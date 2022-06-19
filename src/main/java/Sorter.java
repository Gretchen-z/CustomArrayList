import java.util.Comparator;

/**
 * Интерфейс для сортировки динамического массива типа CustomArrayList
 */
public interface Sorter {
    <T> void sort(CustomArrayList<T> list, Comparator<T> comparator);
}
