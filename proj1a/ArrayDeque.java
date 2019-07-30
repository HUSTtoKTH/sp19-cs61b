public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int lastPointer;
    private int firstPointer;

    public ArrayDeque(){
        items =(T[]) new Object[8];
        size = 0;
        firstPointer = 0;
        lastPointer = 0;
    }
//    : Adds an item of type T to the front of the deque.
    public void addFirst(T item){
        if(size == items.length){
            resize(2*size);
        }
        if(size != 0){
            firstPointer = minusPointer(firstPointer);
        }
        items[firstPointer] = item;
        size++;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        if(firstPointer == 0){
            System.arraycopy(items,0,a,0,size);
        }
        if(firstPointer>lastPointer){
            System.arraycopy(items,firstPointer,a,0,size-firstPointer);
            System.arraycopy(items,0,a,size-firstPointer,lastPointer+1);
        }
        firstPointer = 0;
        lastPointer = size - 1;
        items = a;
    }

    private int addPointer(int pointer){
        if(pointer == items.length - 1 ){
            return 0;
        }else{
            return ++pointer;
        }
    }
    private int minusPointer(int pointer){
        if(pointer == 0 ){
            return items.length - 1;
        }else{
            return --pointer;
        }
    }
////    : Adds an item of type T to the back of the deque.
    public void addLast(T item){
        if(size == items.length){
            resize(2*size);
        }
        if (size != 0) {
            lastPointer = addPointer(lastPointer);
        }
        items[lastPointer] = item;
        size++;
    }

////    : Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }
////    : Returns the number of items in the deque.
    public int size(){
        return size;
    }
////: Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
    public void printDeque(){
        if(firstPointer<lastPointer){
            for (int i = 0;i<size;i++){
                System.out.print(items[i]+" ");
            }
        }
        if(firstPointer>lastPointer){
            for (int i = firstPointer;i<items.length;i++){
                System.out.print(items[i]+" ");
            }
            for (int i = 0;i<lastPointer+1;i++){
                System.out.print(items[i]+" ");
            }
        }
    }
////    : Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T firstItem  = items[firstPointer];
        items[firstPointer] = null;
        if(size != 1){
            firstPointer = addPointer(firstPointer);
        }
        size--;
        if(8*size < items.length && size !=0){
            resize(items.length/8);
        }
        return firstItem;
    }
////    : Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        if (size == 0){
            return null;
        }
        T lastItem  = items[lastPointer];
        items[lastPointer] = null;
        if(size != 1){
            lastPointer = minusPointer(lastPointer);
        }
        size--;
        if(8*size < items.length && size !=0){
            resize(items.length/8);
        }
        return lastItem;
    }
////    : Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        int relativeIndex = firstPointer+index;
        if(relativeIndex >= items.length){
            relativeIndex = relativeIndex - items.length;
        }
        return items[relativeIndex];
    }


//    private ArrayDeque(ArrayDeque other){
//        items =(T[]) new Object[8];
//        size = 0;
//        firstPointer = 0;
//        lastPointer = 0;
//        int otherSize = other.size();
//        for(int i = 0; i<otherSize;i++){
//            addLast((T)other.removeFirst());
//        }
//
//    }
}
