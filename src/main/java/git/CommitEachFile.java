package git;

import utils.CommandLineUtils;

public class CommitEachFile {

    public static void main(String[] args) {
        System.out.println("CommandLineTools::Git::CommitEachFile");
        commitEachFile();
    }

    public static String getFilePath(String fileStatus) {
        if (fileStatus == null)
            return "";

        String[] arr = fileStatus.split(" ");
        String filePath = arr[arr.length - 1];
        System.out.println("Filepath: " + filePath);
        return filePath;
    }

    public static String getFileName(String filePath) {
        if (filePath == null)
            return "";

        filePath = getFilePath(filePath);

        String[] splitArr = filePath.split("/");
        String fileName = splitArr[splitArr.length - 1];
        fileName = fileName.split("\\.")[0];
        System.out.println("FileName: " + fileName);
        return fileName;
    }

    public static void commitEachFile() {
        String fileStatusStr = CommandLineUtils.run("git status -s");
        String[] fileStatusArr = fileStatusStr.split("\n");

        for (String fileStatus : fileStatusArr) {
            System.out.println("\n\n");
            CommandLineUtils.run("git add " + getFilePath(fileStatus));
            CommandLineUtils.run("git commit -m " + getFileName(fileStatus));
        }
    }
}