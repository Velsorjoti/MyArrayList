package interfaceandrealization;
import myexception.NonCorrectIndexException;
import myexception.OhYourElementNotFoundExeption;
import java.util.Arrays;
public class StringListImpl implements StringList<String> {
    private static final int CAPACITY = 10;
    private String[] massiv;
    private int size;

    public StringListImpl() {
        this.massiv = new String[CAPACITY];
        this.size = 0;
    }

    public StringListImpl(int initCapacity) {
        this.massiv = new String[initCapacity];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = massiv.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity / 3 * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            massiv = Arrays.copyOf(massiv, newCapacity);
        }
    }

    @Override
    public String add(String item) {
        ensureCapacity(size + 1);
        massiv[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);
        ensureCapacity(size + 1);
        System.arraycopy(massiv, index, massiv, index + 1, size - index);
        massiv[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);
        String oldString = massiv[index];
        massiv[index] = item;
        return oldString;
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < size; i++) {
            if ((massiv[i].toLowerCase().trim())
                    .equals(item.toLowerCase().trim())) {
                for (int j = i; j < size - 1; j++) {
                    massiv[j] = massiv[j + 1];
                }
                massiv[size - 1] = null;
                size--;
                return item;
            }
        }
        throw new OhYourElementNotFoundExeption(item + " не найден");
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index > size)
            throw new NonCorrectIndexException("Индекс: " + index + ", Размер: " + size);

        String oldString = massiv[index - 1];

        for (int i = index; i < size - 1; i++) {
            massiv[i] = massiv[i + 1];
        }

        massiv[size - 1] = null;
        size--;
        return oldString;
    }

    @Override
    public boolean contains(String item) {
        if(item == null)
            throw new NullPointerException("Ничего не передано");
        for (int i = 0; i < size; i++) {
            if ((massiv[i].toLowerCase().trim())
                    .equals(item.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if(item == null)
            throw new NullPointerException("Ничего не передано");
        for (int i = 0; i < size; i++) {
            if ((massiv[i].toLowerCase().trim())
                    .equals(item.toLowerCase().trim())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if(item == null)
            throw new NullPointerException("Ничего не передано");
        for (int i = size - 1; i >= 0; i--) {
            if ((massiv[i].toLowerCase().trim())
                    .equals(item.toLowerCase().trim())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
            if (index >= 0 && index < size) {
                return massiv[index];
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
            if (!massiv[i].equals(otherList.get(i))) {
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
        if (size == 0)
        return true;
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            massiv[i] = null;
        }
        size = 0;
    }



    @Override
    public String toArray() {
        String[] newmassive = new String[size];
        System.arraycopy(massiv, 0, newmassive, 0, size);
        return Arrays.toString(newmassive);
    }
}
