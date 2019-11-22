
public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /** Creates an empty deque.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;

    }

    /** Resize the underling array to the target capacity.*/
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int beginning = plusOne(nextFirst);
        int end = minusOne(nextLast);
        if (beginning <= end) {
            System.arraycopy(items, beginning, newArray, 0, size);
        } else {
            int sizeOfFirstHalf = items.length - beginning;
            int sizeOfSecondHalf = size - sizeOfFirstHalf;
            System.arraycopy(items, beginning, newArray, 0, sizeOfFirstHalf);
            System.arraycopy(items, 0, newArray, sizeOfFirstHalf, sizeOfSecondHalf);
        }

        nextFirst = newArray.length - 1;
        nextLast = size;
        items = newArray;
    }


    /** Gets the ith item in the list.*/
    @Override
    public T get(int i) {
        if (i >= items.length) {
            return null;
        } else if (nextFirst < nextLast - 1) {
            return items[nextFirst + 1 + i];
        } else {
            if (nextFirst + 1 + i < items.length) {
                return items[nextFirst + 1 + i];
            } else {
                return items[i - (items.length - nextFirst - 1)];
            }
        }

    }

    /** return the number of items in the list.*/
    @Override
    public int size() {
        return size;
    }

    /** Add an element to the head of the array. */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    private int minusOne(int i) {
        if (i - 1 < 0) {
            return items.length - 1;
        }
        return i - 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }
    /** Returns the index immediately after a given index.*/
    private int plusOne(int index) {
        if (index + 1 > items.length - 1) {
            return 0;
        }
        return index + 1;

    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);

        T removed = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return removed;

    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T removed = items[nextLast];
        items[nextLast] = null;
        size--;
        return removed;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /*
    Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (T p:items) {
            System.out.print(p + " ");
        }
        System.out.print("\n");
    }


}