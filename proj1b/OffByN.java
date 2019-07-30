public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int i = Math.abs(x-y);
        return i == N;
    }
}
