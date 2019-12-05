package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineUtils {

    public static String runAsString(String command) {
        System.out.println("Running Command: " + command);
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            while (read.ready()) {
                String line = read.readLine();
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String run(String command) {
        return run(convertCommandToArr(command));
    }

    public static String run(String[] command) {
        System.out.println("Running Command: " + Arrays.asList(command));
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(command);
            Process proc = processBuilder.start();
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            while (read.ready()) {
                String line = read.readLine();
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String[] convertCommandToArr(String command) {
        System.out.println("Input Command: " + command);
        String[] arr = command.split(" ");
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean buildingParam = false;
        for (String item : arr) {
            if (item.startsWith("'")) {
                buildingParam = true;
            }
            if (item.endsWith("'")) {
                buildingParam = false;
                sb.append(item);
                list.add(sb.toString());
                sb = new StringBuilder();
            } else if (buildingParam) {
                sb.append(item).append(" ");
            } else {
                list.add(item);
            }
        }
        System.out.println("Output Command: " + list);
        return list.toArray(new String[list.size()]);
    }
}
