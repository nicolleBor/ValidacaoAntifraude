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

    private static final String NOME_REGEX = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?:[- ][A-ZÀ-Ÿ][a-zà-ÿ]+)*(?: (?:da|de|do|dos|das|de la|de las)" +
            " [A-ZÀ-Ÿ][a-zà-ÿ]+| [A-ZÀ-Ÿ][a-zà-ÿ]+(?:[- ][A-ZÀ-Ÿ][a-zà-ÿ]+)*)+$";
    private static final Pattern PATTERN_NOME = Pattern.compile(NOME_REGEX);
    private static final String TELEFONE_REGEX = "^(\\+\\d{1,3}\\s?)?(\\(?\\d{2}\\)?\\s?)?\\d{4,5}-?\\d{4}$";
    private static final Pattern PATTERN_TELEFONE = Pattern.compile(TELEFONE_REGEX);
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);
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
            System.out.println("cpf: " + false);
            return false;
        }

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

        if(digito10.equals(cpf.substring(9,10)) && digito11.equals(cpf.substring(10,11))){
            System.out.println("cpf: " + true);
            return true;
        } else {
            System.out.println("cpf: " + false);
            return false;
        }

    }

    public boolean validaNomeCompleto(String nomeCompleto){
        if(nomeCompleto == null || nomeCompleto.trim().isEmpty()){
            System.out.println("nome completo: " + false);
            return false;
        }
        boolean retorno = PATTERN_NOME.matcher(nomeCompleto.trim()).matches();
        System.out.println("nome completo: " + retorno);
        return retorno;
    }

    public boolean validaTelefone(String telefone){
        if(Utilities.limpaCaractere(telefone) == null || telefone.trim().isEmpty()){
            System.out.println("telefone: " + false);
            return false;
        }
        boolean retorno = PATTERN_TELEFONE.matcher(telefone.trim()).matches();
        System.out.println("telefone: " + retorno);
        return retorno;
    }

    public boolean validaEmail(String email){
        if(email == null || email.trim().isEmpty()){
            System.out.println("email: " + false);
            return false;
        }
        boolean retorno = PATTERN_EMAIL.matcher(email.trim()).matches();
        System.out.println("email: " + retorno);
        return retorno;
    }

    public boolean validaDataNascimento(String dataNascimento){
        if(Utilities.limpaCaractere(dataNascimento) == null || dataNascimento.trim().isEmpty()) {
            System.out.println("data de nascimento: " + false);
            return false;
        }

        if(!PATTERN_DATA.matcher(dataNascimento).matches()) {
            System.out.println("data de nascimento: " + false);
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate data = LocalDate.parse(dataNascimento,formatter);
            LocalDate hoje = LocalDate.now();

            if(data.getYear() < 1900 || data.isAfter(hoje)) {
                System.out.println("data de nascimento: " + false);
                return false;
            }
            System.out.println("data de nascimento: " + true);
            return true;
        } catch(DateTimeParseException e){
            System.out.println("data de nascimento: " + false);
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

        System.out.println("Endereço: " + retorno);
        return retorno;
    }

    public boolean validaNomeMae(String nomeMae){
        if(nomeMae == null || nomeMae.trim().isEmpty()){
            System.out.println("nome da mae: " + false);
            return false;
        }
        boolean retorno = PATTERN_NOME.matcher(nomeMae.trim()).matches();
        System.out.println("nome da mae: " + retorno);
        return retorno;
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

