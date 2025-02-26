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
    private static final String TELEFONE_REGEX = "^(\\(?\\d{2}\\)?\\s?)?\\d{4,5}-?\\d{4}$";
    private static final Pattern PATTERN_TELEFONE = Pattern.compile(TELEFONE_REGEX);

    // Expressão para validação de e-mails
    // - Aceita letras, números e caracteres especiais antes do "@"
    // - Domínio pode conter letras, números e pontos
    // - Extensão deve ter pelo menos 2 caracteres (ex: .com, .br, .org)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);


    // Expressão para validaçãp de datas no formato DD/MM/AAAA
    // - Dia: 01 a 31
    // - Mês: 01 a 12
    // - Ano: Qualquer sequência de 4 dígitos
    private static final String DATA_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    private static final Pattern PATTERN_DATA = Pattern.compile(DATA_REGEX);

    public ValidacaoAntifraudeController(){
        super();
    }

    public List<String> getCpfsInvalidos(){
        return List.of("00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555",
                "66666666666", "77777777777", "88888888888", "99999999999");
    }

    public boolean validaCpf(String cpf) {
        if(Utilities.limpaCaractere(cpf) == null){
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

    public boolean validaNomeCompleto(String nomeCompleto){
        if(nomeCompleto == null || nomeCompleto.trim().isEmpty()){
            return false;
        }
        return PATTERN_NOME.matcher(nomeCompleto.trim()).matches();
    }

    public boolean validaTelefone(String telefone){
        if(Utilities.limpaCaractere(telefone) == null || telefone.trim().isEmpty()){
            return false;
        }
        return PATTERN_TELEFONE.matcher(telefone.trim()).matches();
    }

    public boolean validaEmail(String email){
        if(email == null || email.trim().isEmpty()){
            return false;
        }
        return PATTERN_EMAIL.matcher(email.trim()).matches();
    }

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

    public boolean validaNomeMae(String nomeMae){
        if(nomeMae == null || nomeMae.trim().isEmpty()){
            return false;
        }
        return PATTERN_NOME.matcher(nomeMae.trim()).matches();
    }

    public int validacaoAntifraude(Cliente cliente) throws Exception {
        int nota;
        Random random = new Random();
        if(validaCpf(cliente.getCpf()) && validaNomeCompleto(cliente.getNomeCompleto()) && validaTelefone(cliente.getTelefone())
                && validaEmail(cliente.getEmail()) && validaDataNascimento(cliente.getDataNascimento())
                && validaEndereco(cliente.getEndereco()) && validaNomeMae(cliente.getNomeMae())) {
            nota = random.nextInt(11);
        } else {
            nota = 0;
        }
        return nota;
    }
}

