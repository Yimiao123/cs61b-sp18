public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /*
    creates an empty deque.
     */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        if(nextFirst == 7){
            nextLast = 0;
        }else{
            nextLast = nextFirst + 1;
        }

    }
    /*
    Resize the underling array to the target capacity.
     */
    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        if (nextLast > nextFirst){
            System.arraycopy(items, nextFirst + 1, newArray, 0, nextLast - nextFirst - 1);
        }
        else if( nextFirst == nextLast){
            System.arraycopy(items, 0, newArray, 0, nextLast);//?
        }else{
            System.arraycopy(items, nextFirst + 1, newArray,0, items.length - nextFirst - 1);
            System.arraycopy(items, 0, newArray, items.length - nextFirst - 1, nextLast);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newArray;
    }
    /*
    Gets the ith item in the list
     */
    public T get(int i){
        if (i > nextFirst || i < nextLast){
            return items[i];
        }
        return null;
    }
    /*
    return the number of items in the list
     */
    public int size(){
        return size;
    }
    /*
    Add an element to the head of the array.
     */
    public void addFirst(T item){
        if (size == items.length){
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;

    }

    private int minusOne(int i){
        if (i - 1 < 0){
            return items.length - 1;
        }
        return i - 1;
    }

    public void addLast(T item){
        if (size == items.length){
            resize( 2 * size);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    private int plusOne(int index){
        if (index + 1 > items.length -1){
            return 0;
        }
        return index + 1;

    }

    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }
        if(items.length >= 16 && (float)size / items.length < 0.25){
            resize(items.length/2);
        }
        nextFirst = plusOne((nextFirst));
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return removed;

    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        if(items.length >= 16 && (float)size/items.length < 0.25){
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T removed = items[nextLast];
        items[nextLast] = null;
        size --;
        return removed;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    /*
    Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque(){
        int index = 0;
        int i = plusOne(nextFirst);
        while(index < size){
            System.out.print(items[i] + " ");
            i = plusOne(i);
            i++;
        }
    }
}
