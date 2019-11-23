import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void arrayDequeTest() {
        StudentArrayDeque<Integer> s = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> a = new ArrayDequeSolution<Integer>();

        for (int i=0; i < 30; i++) {
            int f = StdRandom.uniform(4);

            switch (f) {
                case 0:
                    Integer addNum = StdRandom.uniform(10);
                    a.addFirst(addNum);
                    s.addFirst(addNum);
                    break;

                case 1:
                    addNum = StdRandom.uniform(10);
                    a.addLast(addNum);
                    s.addLast(addNum);
                    break;

                case 2:
                    if (! a.isEmpty()) {
                        Integer tRamdom = a.removeFirst();
                        Integer sRamdom = s.removeFirst();
                        assertEquals(tRamdom, sRamdom);
                    }
                    break;

                case 3:
                    if (! s.isEmpty()) {
                        Integer tRamdom = a.removeLast();
                        Integer sRamdom = s.removeLast();
                        assertEquals(tRamdom, sRamdom);
                    }
            }

        }
        for (int i = 0; i < s.size(); i++)
            assertEquals(s.get(i), a.get(i));


    }


}
