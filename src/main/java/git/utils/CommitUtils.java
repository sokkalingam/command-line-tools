package git.utils;

public class CommitUtils {

    public static String getFilePath(String fileStatus) {
        if (fileStatus == null)
            return "";

        String[] arr = fileStatus.split(" ");
        String filePath = arr[arr.length - 1];
        System.out.println("Filepath: " + filePath);
        return filePath;
    }

    public static String getFilePathDir(String fileStatus) {
        if (fileStatus == null)
            return "";

        String[] arr = fileStatus.split(" ");
        String filePath = arr[arr.length - 1];
        String[] filePathArr = filePath.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filePathArr.length - 1; i++)
            sb.append(filePathArr[i]).append("/");
        System.out.println("FilepathDir: " + sb.toString());
        return sb.toString();
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

    /**
     * Remove M or D at the beginning of file status arr
     * Ex:  M conf/partnerapiContext.xml -> conf/partnerapiContext.xml
     * @param fileStatusArr
     * @return
     */
    public static void format(String[] fileStatusArr) {
        for (int i = 0; i < fileStatusArr.length; i++) {
            String[] splitArr = fileStatusArr[i].split(" ");
            fileStatusArr[i] = splitArr[splitArr.length - 1];
        }
    }
}
