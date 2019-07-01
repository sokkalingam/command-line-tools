package git;

import utils.CommandLineUtils;

import java.util.ArrayList;
import java.util.List;

public class AutoTagMasterCommit {

    public static void main(String[] args) {
        autoTag();
    }

    public static void autoTag() {
        String allTagsAsString = CommandLineUtils.run("git tag -l");
        String[] allTagsArr = allTagsAsString.split("\n");
        List<String> validTags = getValidTags(allTagsArr);
        sort(validTags);
        String nextTag = getNextTag(validTags.get(0));
        System.out.println("Next tag: " + nextTag);
        CommandLineUtils.run("git tag -a -m " + nextTag + " " + nextTag);
        CommandLineUtils.run("git push --follow-tags origin master");
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
                int one = Integer.valueOf(arr1[i]);
                int two = Integer.valueOf(arr2[i]);

                if (one == two)
                    continue;

                return two - one;
            }

            return 0;
        });
    }

    public static String getNextTag(String lastTag) {
        String[] arr = lastTag.split("\\.");

        StringBuilder sb = new StringBuilder();

        int val = Integer.valueOf(arr[arr.length - 1]) + 1;
        sb.append(val);

        for (int i = arr.length - 2; i >= 0; i--) {
            sb.insert(0, ".").insert(0, arr[i]);
        }

        return sb.toString();
    }



}
