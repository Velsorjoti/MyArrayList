package interfaceandrealization;
import java.lang.Integer;
import myexception.NonCorrectIndexException;
import myexception.OhYourElementNotFoundExeption;

import java.util.Arrays;

public class StringListImplInteger<G> implements StringList<Integer> {
    private static final int CAPACITY = 10;
    private Integer[] massivI;
    private int size;

    public StringListImplInteger() {
        this.massivI = new Integer[CAPACITY];
        this.size = 0;
    }

    public StringListImplInteger(int initCapacity) {
        this.massivI = new Integer[initCapacity];
        this.size = 0;
    }

    private void grow() {
        int oldCapacity = massivI.length;
        int newCapacity;
        newCapacity = oldCapacity / 3 * 2;
        massivI = Arrays.copyOf(massivI, newCapacity);
    }

    @Override
    public Integer add(Integer item) {
        if (massivI.length == size) {
            grow();
            massivI[size++] = item;
            return item;
        }
        massivI[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);
        grow();
        System.arraycopy(massivI, index, massivI, index + 1, size - index);
        massivI[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < 0 || index >= size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);
        Integer oldInteger = massivI[index];
        massivI[index] = item;
        return oldInteger;
    }

    @Override
    public Integer remove(Integer item) {
        for (int i = 0; i < size; i++) {
            if ((massivI[i].equals(item))) {
                for (int j = i; j < size - 1; j++) {
                    massivI[j] = massivI[j + 1];
                }
                massivI[size - 1] = null;
                size--;
                return item;
            }
        }
        throw new OhYourElementNotFoundExeption(item + " не найден");
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);

        Integer oldInteger = massivI[index - 1];

        for (int i = index; i < size - 1; i++) {
            massivI[i] = massivI[i + 1];
        }

        massivI[size - 1] = null;
        size--;
        return oldInteger;
    }


    public void sortSelection() {
        for (int i = 0; i < size - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (massivI[j] < massivI[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(massivI, i, minElementIndex);
        }
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null)
            throw new NullPointerException("Ничего не передано");
        quickSort(massivI,0, size - 1);
        return binarySearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null)
            throw new NullPointerException("Ничего не передано");
        for (int i = 0; i < size; i++) {
            if ((massivI[i].equals(item))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null)
            throw new NullPointerException("Ничего не передано");
        for (int i = size - 1; i >= 0; i--) {
            if ((massivI[i].equals(item))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return massivI[index];
        }
        throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException("Ничего не передано");
        }
        if (otherList.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!massivI[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            massivI[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newmassive = new Integer[size];
        System.arraycopy(massivI, 0, newmassive, 0, size);
        return newmassive;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public  void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private boolean binarySearch(Integer item) {
        int l = 0;
        int r = size - 1;
        while (l <= r) {
            int m = (l - r) / 2;
            if (item.equals(massivI)) {
                return true;
            }
            if (item < massivI[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public void quickSort(Integer[] massivI, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(massivI, begin, end);

            quickSort(massivI, begin, partitionIndex - 1);
            quickSort(massivI, partitionIndex + 1, end);
        }
    }

    private  int partition(Integer[] massiveI, int begin, int end) {
        int pivot = massiveI[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (massiveI[j] <= pivot) {
                i++;

                swapElements(massiveI, i, j);
            }
        }

        swapElements(massiveI, i + 1, end);
        return i + 1;
    }
}
