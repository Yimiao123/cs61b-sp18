public class OffByN implements CharacterComparator {
    /** Declare variable */
    private int n;

    public OffByN(int N) {
        this.n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == n;
    }

}
