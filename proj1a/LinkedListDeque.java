public class LinkedListDeque<T> {
    private class theNode {
        public T item;
        public theNode next;
        public theNode previous;
        public theNode(T i, theNode next,theNode previous) {
            item = i;
            this.next = next;
            this.previous = previous;
        }
    }
//    define parameters

    private theNode sentinel;
    private int size;
//    private theNode last;


//    Creates an empty linked list deque.
    public LinkedListDeque(){
        sentinel = new theNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
//        last = null;
    }
//    Adds an item of type T to the front of the deque.
    public void addFirst(T item){
        theNode newItem = new theNode(item,sentinel.next,sentinel);
        sentinel.next.previous = newItem;
        sentinel.next = newItem;
        size++;
//        last = sentinel.previous;
    }

//    Adds an item of type T to the back of the deque.
    public void addLast(T item){
        theNode newItem = new theNode(item,sentinel,sentinel.previous);
        sentinel.previous.next = newItem;
        sentinel.previous = newItem;
        size++;
    }
//    : Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }
//    Returns the number of items in the deque.
    public int size(){
        return size;
    }
//    Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
    public void printDeque(){
        theNode pointer = sentinel;
        while(pointer.next != sentinel){
            System.out.print(pointer.next.item + " ");
            pointer = pointer.next;
        }
        System.out.println("");
    }
//    :Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.previous = sentinel;
        size--;
        return sentinel.next.item;
    }
//  Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.previous.next = sentinel;
        size--;
        return sentinel.previous.item;
    }
//    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        if(index>size){
            return null;
        }
        theNode pointer = sentinel.next;
        int i = 0;
        while(i<index){
            pointer = pointer.next;
            i++;
        }
        return pointer.item;
    }
//: Creates a deep copy of other
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new theNode(null,sentinel,sentinel);
        size = 0;
        for(int i=0; i<other.size();i++){
            addLast((T)other.get(i));
        }

    }
//     Systemame as get, but uses recursion.
    public T getRecursive(int index){
        if(index<0 || index>size ){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(theNode current, int index){
        if(index == 0){
            return current.item;
        }
        return getRecursiveHelper(current.next,index-1);
    }
}
