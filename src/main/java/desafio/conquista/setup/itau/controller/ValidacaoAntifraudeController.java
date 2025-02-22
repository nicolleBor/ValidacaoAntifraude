package desafio.conquista.setup.itau.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class ValidacaoAntifraudeController {

    private static final String NOME_REGEX = "^[A-ZÀ-Ÿ][a-zà-ÿ]+( [A-ZÀ-Ÿ][a-zà-ÿ]+)+$";
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
        return List.of("00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999");
    }

    public boolean validaCpf(String cpf) {

        char digito10, digito11;
        int soma, resultado, numero, peso;

        if ((cpf.length() != 11) || getCpfsInvalidos().contains(cpf)){
            return false;
        }

        //Cálculo do primeiro dígito verificador
        soma = 0;
        peso = 10;
        for(int i=0; i<9; i++){
            numero = (int)(cpf.charAt(i) - 48);
            soma = soma + (numero * peso);
            peso = peso - 1;
        }

        resultado = 11 - (soma % 11);
        if((resultado == 10) || (resultado == 11)){
            digito10 = 0;
        } else {
            digito10 = (char)(resultado + 48);
        }

        soma = 0;
        peso = 11;
        for(int i=0; i<10; i++){
            numero = (int)(cpf.charAt(i) - 48);
            soma = soma + (numero * peso);
            peso = peso - 1;
        }
        resultado = 11 - (soma % 11);
        if((resultado == 10) || (resultado == 11)){
            digito11 = 0;
        } else {
            digito11 = (char)(resultado + 48);
        }

        if((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))){
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
        if(telefone == null || telefone.trim().isEmpty()){
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
        if(dataNascimento == null || dataNascimento.trim().isEmpty()) {
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



}
