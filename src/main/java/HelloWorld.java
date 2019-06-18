public class HelloWorld {

    public static void main(String[] args) {
        String str = " M src/main/java/utils/CommandLineUtils.java";
        String[] arr = str.split(" ");
        System.out.println(arr[arr.length - 1]);
    }
}
