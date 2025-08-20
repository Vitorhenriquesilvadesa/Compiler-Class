package org.vtko.compilers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String sourceFilepath = "src/main/resources/test.txt";

        String source = readFile(sourceFilepath);
        Lexer lexer = new Lexer();
        TokenStream tokenStream = lexer.scanTokens(source);

        System.out.println(tokenStream.getTokens());
    }

    static String readFile(String filepath) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line = "";

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return content.toString();
    }
}