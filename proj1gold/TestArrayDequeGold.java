import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void arrayDequeTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<Integer>();

        for (int i = 0; i < 30; i += 1) {
            int flag = StdRandom.uniform(4);
            /** Java will freely convert from Integer to int, which is called unboxing
             *  However, only reference types can be null
             *  If you try to automatically convert a null integer to an int, you'll get a NullPointerException
             */
            Integer val = Integer.valueOf(StdRandom.uniform(100));
            if (flag == 0) {
                System.out.println("addFirst(" + val + ")");
                ads.addFirst(val);
                sad.addFirst(val);
            } else {
                if (flag == 1) {
                    System.out.println("addLast(" + val + ")");
                    ads.addLast(val);
                    sad.addLast(val);
                } else {
                    if (flag == 2) {
                        if (!ads.isEmpty()) {
                            System.out.println("removeFirst()");
                            assertEquals(ads.isEmpty(), sad.isEmpty());
                            Integer exp = ads.removeFirst();
                            Integer ac = sad.removeFirst();
                            assertEquals("removeFirst()", exp, ac);
                        }
                    } else {
                        if (!ads.isEmpty()) {
                            System.out.println("removeLast()");
                            assertEquals(ads.isEmpty(), sad.isEmpty());
                            Integer exp = ads.removeLast();
                            Integer act = sad.removeLast();
                            assertEquals("removeLast()", exp, act);
                        }
                    }
                }
            }
        }
    }
}
