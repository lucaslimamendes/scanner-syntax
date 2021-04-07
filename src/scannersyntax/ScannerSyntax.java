
package scannersyntax;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author lucas
 */
public class ScannerSyntax {

    public static void main(String[] args) {
        
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> lines = Files.lines( Paths.get("src/scannersyntax/toScanner.txt"), StandardCharsets.UTF_8)) 
        {
            try (PrintWriter out = new PrintWriter("src/scannersyntax/toScannerCheck.txt")) {
                
                lines.forEach(line -> {
                    Stack<Character> pilha = new Stack<>();

                    for(int i = 0; i < line.length(); i++) {
                        if(line.charAt(i) == '{' || line.charAt(i) == '[' || line.charAt(i) == '('){
                            pilha.push(line.charAt(i));
                        }
                        else if(!pilha.empty() && (
                                (line.charAt(i) == '}' && pilha.peek() == '{') || 
                                (line.charAt(i) == ']' && pilha.peek() == '[') || 
                                (line.charAt(i) == ')' && pilha.peek() == '('))){
                            pilha.pop();
                        }
                        else
                        {
                            pilha.push(line.charAt(i));
                        }
                    }

                    if(pilha.empty()){
                        System.out.println(line + " Ok!");
                        out.println(line + " Ok!");
                    }
                    else{
                        System.out.println(line + " Inválido!");
                        out.println(line + " Inválido!");
                    }
                });
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
    
}
