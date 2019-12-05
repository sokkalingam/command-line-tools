package git;

import git.utils.CommitUtils;
import org.apache.commons.lang3.StringUtils;
import utils.CommandLineUtils;

public class CommitEachFile {

    public static void main(String[] args) {
        System.out.println("CommandLineTools::Git::CommitEachFile");
        commitEachFile();
    }

    public static void commitEachFile() {
        String fileStatusStr = CommandLineUtils.runAsString("git status -s");

        if (StringUtils.isBlank(fileStatusStr))
            return;

        String[] fileStatusArr = fileStatusStr.split("\n");

        for (String fileStatus : fileStatusArr) {
            System.out.println("");
            CommandLineUtils.runAsString("git add " + CommitUtils.getFilePath(fileStatus));
            CommandLineUtils.runAsString("git commit -m " + CommitUtils.getFileName(fileStatus));
            System.out.println("");
        }
    }
}