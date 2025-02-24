package desafio.conquista.setup.itau.service;

import desafio.conquista.setup.itau.models.Endereco;
import desafio.conquista.setup.itau.utils.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class IntegracaoAPI {
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
