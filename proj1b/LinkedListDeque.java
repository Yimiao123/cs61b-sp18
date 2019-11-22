public class LinkedListDeque<T> implements Deque<T> {

    private class StuffNode {

        private T item;
        private StuffNode prev;
        private StuffNode next;

        private StuffNode(T item, StuffNode prev, StuffNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }
    /**Declare variables.*/
    private StuffNode sentinel;
    private int size;

    /**creates an empty linked list deque.*/
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /** Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T item) {
        StuffNode first = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
        size++;
    }
    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        StuffNode last = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev = last;
        last.prev.next = last;
        size++;

    }
    /** return true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /** returns the number of items in the deque.*/
    @Override
    public int size() {
        return size;
    }

    /*
    print the items in the deque from first to last,
    separated by a space. Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /*
    Removes and returns the item at the front of the deque.
    If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        StuffNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;

    }
    /*
    Removes and returns the item at the back of the deque.
    If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        StuffNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        size--;
        return last.item;
    }
    /**Gets the item at the given index. If no such item exists, returns null.*/
    @Override
    public T get(int index) {
        StuffNode currentNode = sentinel.next;
        if (index < 0 || index > size - 1) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        }

        return currentNode.item;
    }

    private T getRecursiveHelper(StuffNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }
    /**Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        StuffNode p = sentinel.next;
        return getRecursiveHelper(p, index);

    }


}