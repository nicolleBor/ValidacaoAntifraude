package desafio.conquista.setup.itau.controller;

import desafio.conquista.setup.itau.models.Cliente;
import desafio.conquista.setup.itau.models.Endereco;
import desafio.conquista.setup.itau.utils.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class ValidacaoAntifraudeController {


    // Expressão para validação de estrutura de Nome Completo
    // - Começa com letra maiúscula, seguida de letras minúsculas (com suporte a acentos).
    // - Permite sobrenomes separados por espaços ou hífens.
    // - Aceita partículas como "da", "de", "do", "dos", "das", "de la", "de las".
    private static final String NOME_REGEX = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?:[- ][A-ZÀ-Ÿ][a-zà-ÿ]+)*(?: (?:da|de|do|dos|das|de la|de las)" +
            " [A-ZÀ-Ÿ][a-zà-ÿ]+| [A-ZÀ-Ÿ][a-zà-ÿ]+(?:[- ][A-ZÀ-Ÿ][a-zà-ÿ]+)*)+$";
    private static final Pattern PATTERN_NOME = Pattern.compile(NOME_REGEX);

    // Expressão para validação números de telefone
    // - Aceita parênteses para DDD opcional ((XX)).
    // - Suporta números com 4 ou 5 dígitos na primeira parte e 4 na segunda (ex: 9999-9999 ou 99999-9999).
    private static final String TELEFONE_REGEX = "^(\\+\\d{1,3}\\s?)?(\\(?\\d{2}\\)?\\s?)?\\d{4,5}-?\\d{4}$";
    private static final Pattern PATTERN_TELEFONE = Pattern.compile(TELEFONE_REGEX);

    // Expressão para validação de e-mails
    // - Aceita letras, números e caracteres especiais antes do "@"
    // - Domínio pode conter letras, números e pontos
    // - Extensão deve ter pelo menos 2 caracteres (ex: .com, .br, .org)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);


    // Expressão para validaçãp de datas no formato DD/MM/AAAA
    private static final String DATA_REGEX = "^(?:"
            + "(0[1-9]|1\\d|2[0-8])/(0[1-9]|1[0-2])/\\d{4}"  // Dias 01-28 para qualquer mês
            + "|29/02/(?:(?:19|20)(?:04|08|[2468][048]|[13579][26])|2000)"  // 29/02 apenas em anos bissextos
            + "|30/(0[13-9]|1[0-2])/\\d{4}"  // Dia 30 permitido para meses que têm 30 ou 31 dias
            + "|31/(0[13578]|1[02])/\\d{4}"  // Dia 31 permitido apenas para meses com 31 dias
            + ")$";
    private static final Pattern PATTERN_DATA = Pattern.compile(DATA_REGEX);

    public ValidacaoAntifraudeController(){
        super();
    }


    //Lista de CPFs inválidos que geram exceção no cálculo de validação.
    public List<String> getCpfsInvalidos(){
        return List.of("00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
                "66666666666", "77777777777", "88888888888", "99999999999");
    }


    /**
     * Valida um CPF utilizando cálculo disponibilizado pelo Ministério da Fazenda
     * @param cpf O CPF a ser validado
     * @return true se o CPF existir, false caso o contrário.
     */
    public boolean validaCpf(String cpf) {
        cpf = Utilities.limpaCaractere(cpf);
        if(cpf == null){
            return false;
        }

        String digito10, digito11;
        int soma, resultado, numero, peso;

        if ((cpf.length() != 11) || getCpfsInvalidos().contains(cpf)){
            return false;
        }

        //Cálculo do dígito 10 do CPF.
        soma = 0;
        peso = 10;
        for(int i=0; i<9; i++){
            numero = Integer.parseInt(cpf.substring(i, i+1));
            soma = soma + (numero * peso);
            peso = peso - 1;
        }

        resultado = 11 - (soma % 11);
        if((resultado == 10) || (resultado == 11)){
            digito10 = String.valueOf(0);
        } else {
            digito10 = String.valueOf(resultado);
        }

        //Cálculo do dígito 11 do CPF.
        soma = 0;
        peso = 11;
        for(int i=0; i<10; i++){
            numero = Integer.parseInt(cpf.substring(i,i+1));
            soma = soma + (numero * peso);
            peso = peso - 1;
        }
        resultado = 11 - (soma % 11);
        if((resultado == 10) || (resultado == 11)){
            digito11 = String.valueOf(0);
        } else {
            digito11 = String.valueOf(resultado);
        }

        //Confere se dígitos calculados batem com os digitados na entrada.
        if(digito10.equals(cpf.substring(9,10)) && digito11.equals(cpf.substring(10,11))){
            return true;
        } else {
            return false;
        }

    }

    /**
     * Valida estrutura esperada de um nome completo utilizando regex.
     * @param nomeCompleto O nome a ser validado.
     * @return true se o nome completo possuir Nome e Sobrenome, false caso o contrário.
     */

    public boolean validaNomeCompleto(String nomeCompleto, String nomeMae){
        if(nomeCompleto == null || nomeCompleto.trim().isEmpty() || nomeCompleto == nomeMae){
            return false;
        }
        return PATTERN_NOME.matcher(nomeCompleto.trim()).matches();
    }



    public List<String> getDDDsValidos(){
        return List.of("11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28",
                "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51",
                "53", "54", "55", "61", "62", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79",
                "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99");
    }

    /**
     * Valida se um telefone possui a estrutura devida utilizando regex.
     * @param telefone O telefone a ser validado.
     * @return true se o telefone cumprir com a estrutura devida, false caso o contrário.
     */
    public boolean validaTelefone(String telefone){

        String ddd = telefone.substring(1,3);

        if(Utilities.limpaCaractere(telefone) == null || telefone.trim().isEmpty()){
            return false;
        }

        if(!getDDDsValidos().contains(ddd)){
            return false;
        }

        return PATTERN_TELEFONE.matcher(telefone.trim()).matches();
    }

    /**
     * Valida um endereço de e-mail utilizando regex.
     * @param email O e-mail a ser validado.
     * @return true se o e-mail for válido, false caso o contrário.
     */

    public boolean validaEmail(String email){
        if(email == null || email.trim().isEmpty()){
            return false;
        }
        return PATTERN_EMAIL.matcher(email.trim()).matches();
    }

    /**
     * Valida se a data digitada possui o formato dd/MM/yyyy utilizando regex, além de averiguar se a data de nascimento
     * aplicada é coerente conforme data atual.
     * @param dataNascimento A data de nascimento a ser validada.
     * @return true se a data de nascimento for válida, false caso o contrário.
     */

    public boolean validaDataNascimento(String dataNascimento){
        if(Utilities.limpaCaractere(dataNascimento) == null || dataNascimento.trim().isEmpty()) {
            return false;
        }

        if(!PATTERN_DATA.matcher(dataNascimento).matches()) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate data = LocalDate.parse(dataNascimento,formatter);
            LocalDate hoje = LocalDate.now();

            if(data.getYear() < 1900 || data.isAfter(hoje)) {
                return false;
            }
            return true;
        } catch(DateTimeParseException e){
            return false;
        }
    }

    /**
     * Consome API viaCEP para captar endereço conforme CEP fornecido, validando na sequência se todos os dados
     * requetidos foram preenchidos.
     * @param endereco O endereço a ser validado.
     * @return true caso esteja válido, false caso contrário.
     * @throws Exception Se houver falha na conexão com a API ViaCEP
     */

    public boolean validaEndereco(Endereco endereco) throws Exception {
        Endereco enderecoApi = IntegracaoAPIController.buscaCep(endereco.getCep());

        if(Utilities.limpaCaractere(endereco.getCep()) == null){
            return false;
        }

        boolean retorno = enderecoApi != null;

        if (endereco.getLogradouro().isEmpty() || endereco.getBairro().isEmpty() || endereco.getLocalidade().isEmpty()
            || endereco.getUf().isEmpty() || endereco.getComplemento().isEmpty() || endereco.getUnidade().isEmpty()){
            retorno = false;
        }

        return retorno;
    }

    /**
     * Valida se Nome da Mãe possui estrutura de nome completo utilizando regex.
     * @param nomeMae O nome da mãe a ser validado.
     * @return true caso esteja válido, false caso contrário.
     */

    public boolean validaNomeMae(String nomeMae, String nomeCompleto){
        if(nomeMae == null || nomeMae.trim().isEmpty() || nomeMae == nomeCompleto){
            return false;
        }
        return PATTERN_NOME.matcher(nomeMae.trim()).matches();
    }

    /**
     * Realiza apuração do grau de confiabilidade do cliente conforme dados validados anteriormente.
     * @param cliente O objeto principal da análise.
     * @return nota randômica entre 0 a 10 caso todas as validações retornem true, nota 0 caso exista algum false dentre
     * as validações.
     * @throws Exception
     */

    public int validacaoAntifraude(Cliente cliente) throws Exception {
        int nota;
        Random random = new Random();
        if(validaCpf(cliente.getCpf()) && validaNomeCompleto(cliente.getNomeCompleto(), cliente.getNomeMae()) && validaTelefone(cliente.getTelefone())
                && validaEmail(cliente.getEmail()) && validaDataNascimento(cliente.getDataNascimento())
                && validaEndereco(cliente.getEndereco()) && validaNomeMae(cliente.getNomeMae(), cliente.getNomeCompleto())) {
            nota = random.nextInt(11);
        } else {
            nota = 0;
        }
        return nota;
    }
}

