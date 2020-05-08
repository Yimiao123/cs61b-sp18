package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<>(5);
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        arb.dequeue();
        arb.enqueue(4.4);
        arb.enqueue(1.1);
        assertEquals(44.8, arb.dequeue());
        assertEquals(62.3, arb.peek());
        assertEquals(62.3, arb.dequeue());

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        arb.dequeue();
        arb.enqueue(4.4);
        arb.enqueue(1.1);
        Iterator<Double> iter = arb.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }



    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
