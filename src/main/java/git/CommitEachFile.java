package git;

import utils.CommandLineUtils;

public class CommitEachFile {

    public static void main(String[] args) {
        System.out.println("CommandLineTools::Git::CommitEachFile");
        commitEachFile();
    }

    public static String getFileName(String filePath) {
        if (filePath == null)
            return "";

        String[] splitArr = filePath.split("/");
        String fileName = splitArr[splitArr.length - 1];
        return fileName.split("\\.")[0];
    }

    public static void commitEachFile() {
        String filePathsString = CommandLineUtils.run("git diff --name-only");
        String[] filePathsArr = filePathsString.split("\n");

        for (String filePath : filePathsArr) {
            CommandLineUtils.run("git add " + filePath);
            CommandLineUtils.run("git commit -m '" + getFileName(filePath) + "'");
        }
    }
}