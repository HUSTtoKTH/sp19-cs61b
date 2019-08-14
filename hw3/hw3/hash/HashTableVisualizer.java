package hw3.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1.0;
        int N = 100;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> param1 = new ArrayList<>(Arrays.asList(1,0,0,0,0));
        List<Integer> param2 = new ArrayList<>(Arrays.asList(2,0,0,0,0));
        List<Integer> param3 = new ArrayList<>(Arrays.asList(3,0,0,0,0));
        List<Integer> param4 = new ArrayList<>(Arrays.asList(4,0,0,0));
        List<Integer> param5 = new ArrayList<>(Arrays.asList(5,0,0,0));
        List<Integer> param6 = new ArrayList<>(Arrays.asList(6,0,0,0));

        // Your code here.
        deadlyList.add(new ComplexOomage(param1));
        deadlyList.add(new ComplexOomage(param2));
        deadlyList.add(new ComplexOomage(param3));
        deadlyList.add(new ComplexOomage(param4));
        deadlyList.add(new ComplexOomage(param5));
        deadlyList.add(new ComplexOomage(param6));
        visualize(deadlyList, M, scale);
//        List<Oomage> oomies = new ArrayList<>();
//        for (int i = 0; i < N; i += 1) {
//           oomies.add(ComplexOomage.randomComplexOomage());
//        }
//        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
