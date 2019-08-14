package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int n = oomages.size();
        double up = n/2.5;
        double down = n/50;
        int[] bucket = new int[M];
        int bucketNum;
        for (Oomage oomage:oomages) {
            bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum]++;
        }
        for (int a:bucket){
            if(a > up || a < down) return false;
        }
        return true;
    }
}
