package hw3.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> param1 = new ArrayList<>(Arrays.asList(1,0,0,0));
        List<Integer> param2 = new ArrayList<>(Arrays.asList(2,0,0,0));
        List<Integer> param3 = new ArrayList<>(Arrays.asList(3,0,0,0));
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


        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
