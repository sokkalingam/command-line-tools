package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineUtils {

    public static String run(String command) {
        try {
            Process proc = Runtime.getRuntime().exec(command); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            StringBuilder sb = new StringBuilder();
            while (read.ready()) {
                String line = read.readLine();
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
