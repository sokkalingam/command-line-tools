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
        String fileStatusStr = CommandLineUtils.run("git diff --name-only");

        if (StringUtils.isBlank(fileStatusStr))
            return;

        String[] fileStatusArr = fileStatusStr.split("\n");

        for (String fileStatus : fileStatusArr) {
            System.out.println("");
            CommandLineUtils.run("git add " + CommitUtils.getFilePath(fileStatus));
            CommandLineUtils.run("git commit -m " + CommitUtils.getFileName(fileStatus));
            System.out.println("");
        }
    }
}