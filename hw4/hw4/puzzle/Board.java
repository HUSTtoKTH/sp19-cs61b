package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[][] cowmoo;
    private final int BLANK = 0;


    public Board(int[][] tiles){
        cowmoo = new int[tiles.length][tiles[0].length];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                cowmoo[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i, int j){
        if(i < 0 || i > size() || j < 0 || j > size()){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return cowmoo[i][j];
    }
    public int size(){
        return cowmoo.length;
    }
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int wrongPosition = 0;
        for(int i = 0; i < size(); i++){
            for(int j = 0; j < size(); j++){
                if( i == (size()-1) && j == (size()-1))continue;
                if(cowmoo[i][j] != (i * size() + j + 1))wrongPosition++;
            }
        }
        return wrongPosition;
    }
    public int manhattan(){
        int sum = 0;
        for(int i = 0; i < size(); i++){
            for(int j = 0; j < size(); j++){
                if(cowmoo[i][j] != 0){
                    int vertical = (cowmoo[i][j] - 1)/size();
                    int horizontal = (cowmoo[i][j] - 1)%size();
                    sum += (Math.abs(vertical - i) + Math.abs(horizontal - j));
                }
            }
        }
        return sum;
    }
    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public boolean equals(Object y){
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board compare = (Board) y;
        if (size() !=  compare.size()) {return false;}
        if (compare == null || compare.cowmoo == null)return false;
        for(int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (cowmoo[i][j] != compare.cowmoo[i][j]) return false;
            }
        }
        return true;
    }
    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
