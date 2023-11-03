import java.util.Arrays;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] array;
    private int size;

    public MyArrayList() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        this.array = (T[]) new Object[initialCapacity];
        size = 0;
    }

    public void add(T value) {
        if (size >= array.length * 0.7) {
            array = Arrays.copyOf(array, array.length * 3 / 2 + 1);
        }
        array[size] = value;
        size++;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    public T remove(int index) {
        T oldValue = array[index];
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T item : array) {
            if (item != null) {
                sb.append(item).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}

class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> myList = new MyArrayList<>();
        for (int i = 1; i < 17; i++) {
            myList.add(String.valueOf(i));
        }
        System.out.println("Size of array: " + myList.size());
        System.out.println(myList);
        System.out.println("Get by index: " + myList.get(1));
        System.out.println("Remove element: " + myList.remove(5));
        System.out.println("Size of array: " + myList.size());
        System.out.println(myList);
        myList.clear();
        System.out.println(myList);
        myList.add("12");
        myList.add("13");
        System.out.println(myList);
        System.out.println("Size of array: " + myList.size());
    }
}
