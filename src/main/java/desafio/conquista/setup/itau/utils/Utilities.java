package desafio.conquista.setup.itau.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class Utilities {
    public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException{
        String resposta, jsonString = "";
        while((resposta = bufferedReader.readLine()) != null){
            jsonString += resposta;
        }
        return jsonString;
    }

    public static String limpaCaractere(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}
