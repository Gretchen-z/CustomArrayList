import java.util.Comparator;

/**
 * Класс, реализующий алгоритм быстрой сортировки для динамического массива типа CustomArrayList
 */
public class QuickSorterImpl implements Sorter {

    /**
     * Метод быстрой сортировки динамического массива
     * @param list - сортируемый динамический массив
     * @param comparator
     */
    @Override
    public <T> void sort(CustomArrayList<T> list, Comparator<T> comparator) {
        T[] array = list.toArray();
        quickSort(array, 0, array.length - 1, comparator);
        for (int i = 0; i < array.length; i++) {
            list.setToArray(i, array[i]);
        }
    }

    private <T> void quickSort(T[] array, int from, int to, Comparator<T> comparator) {
        if (from >= to) return;

        int divider = partition(array, from, to, comparator);
        quickSort(array, from, divider - 1, comparator);
        quickSort(array, divider + 1, to, comparator);
    }

    private <T> int partition(T[] array, int from, int to, Comparator<T> comparator) {
        int leftIndex = from;
        int rightIndex = to;

        T pivot = array[from + (to - from) / 2];

        while (leftIndex < rightIndex) {
            while ((comparator.compare(array[leftIndex], pivot)) < 0) {
                leftIndex++;
            }

            while ((comparator.compare(array[rightIndex], pivot)) > 0) {
                rightIndex--;
            }

            if (leftIndex < rightIndex) {
                swap(array, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
