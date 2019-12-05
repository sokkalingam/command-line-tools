package git;

import utils.CommandLineUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class GreenLandCommit {

    private static final String fileName = "temp-commits.log";

    public static void main(String[] args) {

        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);

        for (int i = start; i <= end; i++)
            commitDaysAgo(i);

    }

    public static void commitDaysAgo(int daysAgo) {
        appendToFile();
        CommandLineUtils.run("git add " + fileName);
        CommandLineUtils.run("git commit -m '" + daysAgo + " day ago' --date '" + daysAgo + " day ago'");
    }

    public static void appendToFile() {
        FileWriter fileWriter = null; //Set true for append mode
        try {
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(new Date());  //New line
        printWriter.close();
    }




}
