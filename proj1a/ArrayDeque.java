public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /*
    create an empty list.
     */
    public ArrayDeque()    {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;

    }
    /*
    Resize the underling array to the target capacity.
     */
    public void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        if (nextLast > nextFirst){
            System.arraycopy(items, nextFirst+1, newArray, 0, nextLast - nextFirst -1);
        }
        else if( nextFirst == nextLast)
        {
            System.arraycopy(items, 0, newArray, 0, nextLast);//?
        }else
            {
            System.arraycopy(items, nextFirst+1, newArray,0, items.length-nextFirst-1);
            System.arraycopy(items, 0, newArray, items.length-nextFirst-1, nextLast);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newArray;
    }
    /*
    Gets the ith item in the list
     */
    public T get(int i){
        if (i < 0 || i >= size){
            return null;
        }
        return items[i];
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
            resize(2*size);
        }
        items[nextFirst] = item;
        size ++;
        nextFirst = minusOne(nextFirst);
    }

    private int minusOne(int i){
        if (i - 1 < 0){
            return items.length - 1;
        }
        return i - 1;
    }

    public void addLast(T item){
        if (size == items.length){
            resize( 2* size);

        }
        items[nextLast] = item;
        size ++;
        nextLast = plusOne(nextLast);
    }

    public int plusOne(int index){
        if (index + 1 > items.length -1){
            return 0;
        }
        return index + 1;

    }

    public T removeFirst(){
        if(isEmpty())
            return null;
        nextFirst = plusOne((nextFirst));
        T removed = items[nextFirst];
        items[nextFirst] = null;

        size --;
        if(items.length > 16 && (float)size/items.length<0.25){
            resize(items.length/2);
        }
        return removed;

    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        nextLast = minusOne(nextLast);
        T removed = items[nextLast];
        items[nextLast] = null;
        size --;
        if(items.length > 16 && (float)size/items.length<0.25){
            resize(items.length/2);
        }
        return removed;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public void printDeque(){
        int i = plusOne(nextFirst);
        while(i != nextLast){
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }
}
