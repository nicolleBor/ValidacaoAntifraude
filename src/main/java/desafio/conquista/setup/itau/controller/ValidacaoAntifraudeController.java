package desafio.conquista.setup.itau.controller;

import desafio.conquista.setup.itau.Interface.IValidacaoAntifraudeController;
import desafio.conquista.setup.itau.models.Cliente;

public class ValidacaoAntifraudeController implements IValidacaoAntifraudeController {

    public ValidacaoAntifraudeController(){
        super();
    }

    @Override
    public boolean validaCpf(String cpf) {

        char digito10, digito11;
        int soma, resultado, numero, peso;

        // Validação de existência de erro no CPF na situação em que for digitada sequência de números iguais.
        if(cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)){
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
            digito10 = (char)(r + 48);
        }

        //Cálculo do segundo dígito verificador
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

        //Validação dos dígitos verificadores calculados com os digitados no campo CPF.
        if((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))){
            return true;
        } else {
            return false;
        }

    }
}
