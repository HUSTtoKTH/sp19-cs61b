public class test {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for(int i = 0; i<10;i++){
            test.addFirst(i);
        }
        for(int i = 0; i<10;i++){
            test.removeFirst();
        }
        for(int i = 0; i<10;i++){
            test.addFirst(i);
        }


//        test.get(0);
//        test.get(0);
//        test.get(1);
//        LinkedListDeque<Integer> test = new LinkedListDeque<>();
//        test.addLast(0);
//        test.removeLast();
//        test.addLast(2);
//        test.addLast(3);
//        test.removeFirst();
//        test.removeLast();
//        test.addFirst(0);
//        test.removeLast();
//        test.addFirst(6);
//        test.removeLast();
//        test.addFirst(1);
//        test.addFirst(2);
//        test.addFirst(3);
//        test.addFirst(4);
//        test.addFirst(5);
//        test.addFirst(6);
//        test.addFirst(7);
//        test.addFirst(8);
//        test.addFirst(9);
//        for(int i = 10;i<18;i++){
//            test.addLast(i);
//        }
//        test.removeFirst();
//        ArrayDeque<Integer> copyTest = new ArrayDeque(test);

    }
}
