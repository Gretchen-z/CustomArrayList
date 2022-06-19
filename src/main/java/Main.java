public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> customList = new CustomArrayList<>();
        customList.addToArray("4");
        customList.addToArray("6");
        customList.addToArray("9");
        customList.addToArray("7");
        customList.addToArray("1");

        Sorter quickSorter = new QuickSorterImpl();
        quickSorter.sort(customList, String::compareTo);

        for (String s : customList) {
            System.out.println(s);
        }
    }
}
