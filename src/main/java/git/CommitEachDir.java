package git;

import git.utils.CommitUtils;
import org.apache.commons.lang3.StringUtils;
import utils.CommandLineUtils;

import javax.activation.CommandMap;
import java.util.Arrays;
import java.util.Comparator;

public class CommitEachDir {

    public static void main(String[] args) {
        System.out.println("CommandLineTools::Git::CommitEachDir");
        commitEachDir();
    }

    public static void commitEachDir() {
        String fileStatusStr = CommandLineUtils.run("git diff --name-only");
        while (StringUtils.isNotBlank(fileStatusStr)) {
            String[] files = fileStatusStr.split("\n");

            CommitUtils.format(files);
            Arrays.sort(files, Comparator.reverseOrder());

            String file = files[0];

            System.out.println("");
            String filePathDir = CommitUtils.getFilePathDir(file);
            String fileToAdd = StringUtils.isNotBlank(filePathDir) ? filePathDir : file;
            CommandLineUtils.run("git add " + fileToAdd);
            CommandLineUtils.run("git commit -m " + fileToAdd);
            System.out.println("");

            fileStatusStr = CommandLineUtils.run("git status -s");
        }
    }

}