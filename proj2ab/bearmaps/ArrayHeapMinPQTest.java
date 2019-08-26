package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest{


    @Test
    public void add_contains_test(){
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 10; i++){
            test.add(i,i);
            assertTrue(test.contains(i));
        }

    }
    @Test
    public void getSmallest_removeSmallest_test(){
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for(int i = 10; i >= 0; i--){
            test.add(i,i);
            assertEquals(test.getSmallest(),(Object)i);
        }
        for(int i = 0; i <= 10; i++){
            assertEquals(test.removeSmallest(),(Object)i);
        }
    }
    @Test
    public void changePriority_test(){
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for(int i = 0; i <= 10; i++){
            test.add(i,i);
        }
        for(int i = 0; i <= 10; i++){
            test.changePriority(i, -i);
            assertEquals(test.getSmallest(),(Object)i);
        }

    }

    public static Object[] forTest(ArrayHeapMinPQ<Integer> test){
        Object[] forTest = new Object[test.pq.size()];
        for(int i = 1; i < test.pq.size(); i++){
            forTest[i] = test.pq.get(i).getItem();
        }
        return forTest;
    }
    public static void main(String[] args) {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for(int i = 0; i <= 10; i++){
            test.add(i,i);
        }
        PrintHeapDemo.printFancyHeapDrawing(forTest(test));
        test.changePriority(10,-1);
        PrintHeapDemo.printFancyHeapDrawing(forTest(test));



//        long start = System.currentTimeMillis();
//        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 200000; i += 1) {
//            minHeap.add(i, 100000 - i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");
//
//        long start2 = System.currentTimeMillis();
//        for (int j = 0; j < 200000; j += 1) {
//            minHeap.changePriority(j, j + 1);
//        }
//        long end2 = System.currentTimeMillis();
//        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");



    }

}
