public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> customList = new CustomArrayList<>();
        customList.addToArray("fff");
        customList.addToArray("erer");
        customList.addToArray("hhh");
        customList.addToArray("dfv");
        customList.addToArray("ooooo");

        customList.quickSort(customList, String::compareTo);
    }
}
