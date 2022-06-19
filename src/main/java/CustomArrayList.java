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
     * Конструктор для создания динамического массива из переменного числа аргументов
     * @param content
     */
    public CustomArrayList(T ... content) {
        array = content;
        size = array.length;
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

    public void setToArray(int index, T element) {
        array[index] = element;
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
     * Метод для получения внутреннего подмассива динамического массива
     * @return внутренний подмассив
     */
    public T[] toArray() {
        return Arrays.copyOfRange(array, 0, size);
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
