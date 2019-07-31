public class HorribleSteve {
//    @Test
//    public void testFilk(){
//
//        assertTrue("128 wrong",Flik.isSameNumber(128,128));
//        assertTrue(Flik.isSameNumber(500,500));
////        assertTrue(Flik.isSameNumber(null,null));
//        assertFalse(Flik.isSameNumber(128,500));
//    }

    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }


}
