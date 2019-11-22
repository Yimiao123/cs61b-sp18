/**  Project 1B: Applying and Testing Data Structures version 1.0
 *
 *   Task 1: Deque Interface
 *
 *   @author Yimiao Cao 11/21/2019
 *
 * */

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
