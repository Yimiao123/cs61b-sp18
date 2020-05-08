package synthesizer;

import java.util.Iterator;

// an abstract class should be a stricter "is-a" relationship
/*
 *  Methods and classes can be declared as abstract using the abstract keyword.
 * Abstract classes can provide implementation inheritance for features other than public methods, including instance variables
 * If an implementing class fails to implements any abstract methods inherited from an interface, then that class must be declared abstract

 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int capacity;// The keyword protected means
    // that only the subclasses of abstractBoundedQueue and classes in the same package as the abstractBoundedQueue
    // can access this variable
    protected int fillCount;


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean isEmpty() {
        return fillCount() == 0;
    }

    @Override
    public boolean isFull() {
        return capacity() == fillCount();
    }

    @Override
    public abstract void enqueue(T x);

    @Override
    public abstract T dequeue();

    @Override
    public abstract T peek();

    @Override
    public abstract Iterator<T> iterator();
}
