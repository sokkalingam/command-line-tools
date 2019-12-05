package git;

import git.enums.VersionLevel;
import utils.CommandLineUtils;

import java.util.ArrayList;
import java.util.List;

public class AutoTagMasterCommit {

    private static VersionLevel versionLevel = null;

    public static void main(String[] args) {
        if (validate(args)) {
            if (versionLevel == null) versionLevel = VersionLevel.PATCH;
            autoTag();
        } else {
            throw new RuntimeException("Invalid arguments. Valid arguments are one of [MAJOR, MINOR, PATCH] - defaults to PATCH");
        }
    }

    public static boolean validate(String[] args) {
        if (args == null || args.length == 0)
            return true;
        if (args.length > 1)
            return false;
        // Args length has to be 1
        String arg = args[0];
        try {
            versionLevel = VersionLevel.valueOf(arg.toUpperCase());
        } catch(Exception ex) {
            return false;
        }
        return true;
    }

    public static void autoTag() {
        String allTagsAsString = CommandLineUtils.runAsString("git tag -l");
        String[] allTagsArr = allTagsAsString.split("\n");
        List<String> validTags = getValidTags(allTagsArr);
        sort(validTags);
        String nextTag = getNextTag(validTags.get(0), versionLevel);
        System.out.println("Next tag: " + nextTag);
        CommandLineUtils.runAsString("git tag -a -m " + nextTag + " " + nextTag);
        CommandLineUtils.runAsString("git push --follow-tags origin master");
    }

    /**
     * Get tags that do not start with alphabets
     * @param tags
     * @return
     */
    public static List<String> getValidTags(String[] tags) {
        List<String> list = new ArrayList<>();

        for (String item : tags) {
            if (!Character.isLetter(item.charAt(0)))
                list.add(item);
        }

        return list;
    }

    /**
     * Sort the tags in descending order
     * @param list
     */
    public static void sort(List<String> list) {
        list.sort((item1, item2) -> {
            String[] arr1 = item1.split("\\.");
            String[] arr2 = item2.split("\\.");

            for (int i = 0; i < Math.min(arr1.length, arr2.length); i++) {
                int one = parseInt(arr1[i]);
                int two = parseInt(arr2[i]);

                if (one == two)
                    continue;

                return two - one;
            }

            return 0;
        });
    }
    
    public static int parseInt(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getNextTag(String lastTag, VersionLevel versionLevel) {
        String[] arr = lastTag.split("\\.");

        StringBuilder sb = new StringBuilder();

        if (versionLevel == VersionLevel.PATCH) {
            int val = parseInt(arr[2]) + 1;
            sb.append(arr[0]).append(".").append(arr[1]).append(".").append(val);
        } else if (versionLevel == VersionLevel.MINOR) {
            int val = parseInt(arr[1]) + 1;
            sb.append(arr[0]).append(".").append(val).append(".").append(0);
        } else if (versionLevel == VersionLevel.MAJOR) {
            int val = parseInt(arr[0]) + 1;
            sb.append(val).append(".").append(0).append(".").append(0);
        }

        return sb.toString();
    }

}
