package git;

import git.utils.CommitUtils;
import org.apache.commons.lang3.StringUtils;
import utils.CommandLineUtils;

import java.util.Arrays;
import java.util.Comparator;

public class CommitEachDir {

    public static void main(String[] args) {
        System.out.println("CommandLineTools::Git::CommitEachDir");
        commitEachDir();
    }

    public static void commitEachDir() {
        String fileStatusStr = CommandLineUtils.runAsString("git status -s");
        while (StringUtils.isNotBlank(fileStatusStr)) {
            String[] files = fileStatusStr.split("\n");

            CommitUtils.format(files);
            Arrays.sort(files, Comparator.reverseOrder());

            String file = files[0];

            System.out.println("");
            String filePathDir = CommitUtils.getFilePathDir(file);
            String fileToAdd = StringUtils.isNotBlank(filePathDir) ? filePathDir : file;
            CommandLineUtils.runAsString("git add " + fileToAdd);
            CommandLineUtils.runAsString("git commit -m " + fileToAdd);
            System.out.println("");

            fileStatusStr = CommandLineUtils.runAsString("git status -s");
        }
    }

}