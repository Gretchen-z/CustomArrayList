import java.util.*;

/**
 * Класс для создания динамического массива
 * @param <T>
 */
public class CustomArrayList<T> extends AbstractList<T> {
    private T[] array;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор для создания динамического массива с заданной величиной внутреннего массива
     * @param capacity длина внутреннего массива
     */
    public CustomArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        } else {
            array = (T[]) new Object[capacity];
        }
    }

    /**
     * Конструктор для создания динамического массива по умолчанию
     */
    public CustomArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод для добавления элемента в конец динамического массива
     * @param element
     */
    public void addToArray(T element) {
        if (size == array.length){
            array = (T[]) grow();
        }

        array[size++] = element;
    }

    /**
     * Метод для добавления элемента в динамический массив на определённое место
     * @param index
     * @param element
     */
    public void addToArray(int index, T element) {
        if (size == array.length){
            array = (T[]) grow();
        }

        for (int i = size; i > index; i--) {
            array[1] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    /**
     * Метод для удаления элемента из динамического массива по индексу
     * @param index
     */
    public void delete(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index must be less than size");
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i+1];
        }
        size--;
    }

    /**
     * Метод для удаления элемента из динамического массива по элементу
     * @param element
     */
    public void delete(T element) {
        int ind = index(element);
        if (ind < 0) {
            return;
        }
        delete(ind);
    }

    /**
     * Метод для получения размера динамического массива
     * @return искомый размер
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод для получения элемента динамического массива по индексу
     * @param index
     * @return искомый элемент
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index must be less than size");
        }
        return array[index];
    }

    /**
     * Метод быстрой сортировки динамического массива
     * @param list - сортируемый динамический массив
     * @param comparator
     */
    public void quickSort(CustomArrayList<T> list, Comparator<T> comparator){
        quickSort(list.array, 0, list.size - 1, comparator);
    }

    private void quickSort(T[] array, int from, int to, Comparator<T> comparator) {
        if (from >= to) return;

        int divider = partition(array, from, to, comparator);
        quickSort(array, from, divider - 1, comparator);
        quickSort(array, divider + 1, to, comparator);
    }

    private int partition(T[] array, int from, int to, Comparator<T> comparator) {
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

            if (leftIndex <= rightIndex) {
                swap(array, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        return leftIndex;
    }

    private void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private Object[] grow() {
        int capacity = array.length;
        int newCapacity = (capacity * 3) / 2 + 1;
        return array = Arrays.copyOf(array, newCapacity);
    }

    private int index(T element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if(array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
