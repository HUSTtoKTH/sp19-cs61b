public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        int i = Math.abs(x-y);
        return i==1;
    }
}
