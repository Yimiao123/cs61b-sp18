import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        CharacterComparator offByN = new OffByN(5);
        assertFalse(offByN.equalChars('a','b'));
    }

    @Test
    public void testEqualChars2() {

        CharacterComparator OffByN = new OffByN(5);

        assertTrue(OffByN.equalChars('a', 'f'));

    }
}
