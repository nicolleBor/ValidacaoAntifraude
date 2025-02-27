package desafio.conquista.setup.itau.controller;

import desafio.conquista.setup.itau.models.Endereco;
import desafio.conquista.setup.itau.utils.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class IntegracaoAPIController {

    /**
     * Realiza busca de dados de endereço por meio de consumo de API viaCEP.
     * @param cep O CEP a ser consultado (apenas números, sem formatação).
     * @return Um objeto {@code Endereco} contendo as informações do endereço correspondente ao CEP.
     *      Retorna {@code null} se ocorrer algum erro na requisição ou na conversão do JSON.
     * @throws Exception Se houver falha na conexão com a API ViaCEP
     */

    public static Endereco buscaCep(String cep) throws Exception{
        String enderecoURL = "https://viacep.com.br/ws/" + cep + "/json/";
        URL url = new URL(enderecoURL);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);

        try{
            BufferedReader buff = new BufferedReader(new InputStreamReader((conexao.getInputStream()), "utf-8"));

            String convertJsonString = Utilities.converteJsonEmString(buff);
            Gson gson = new Gson();

            return gson.fromJson(convertJsonString, Endereco.class);
        }
        catch (Exception exception){
           return null;
        }
    }
}
