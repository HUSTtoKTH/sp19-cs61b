package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    ArrayList<PriorityNode> pq;
    private HashMap<T, Integer> itemMap;

    public ArrayHeapMinPQ() {
        this.pq = new ArrayList<>();
        this.itemMap = new HashMap<>();
        pq.add(0, null);
    }

    @Override
    public void add(T item, double priority) {
        if(contains(item)){
            throw new IllegalArgumentException();
        }
        pq.add(new PriorityNode(item,priority));
        itemMap.put(item, size());
        swim(size());
    }
    private void swim(int child){
        int parent = child / 2;
        if(parent == 0) return;
        if (smaller(child, parent)) {
            Collections.swap(pq, child, parent);
            itemMap.put(pq.get(child).getItem(), child);
            itemMap.put(pq.get(parent).getItem(), parent);
            swim(parent);
        }
    }
    private boolean smaller(int i, int j) {
        return pq.get(i).compareTo(pq.get(j)) < 0;
    }

    @Override
    public boolean contains(T item) {
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq.get(1).getItem();
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T removeSmallest() {
        if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        T smallest = getSmallest();
        Collections.swap(pq, 1, size());
        pq.remove(size());
        sink(1);
        return smallest;
    }
    private void sink(int index){
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        int small = leftChild;
        if(small > size()) return;
        if(rightChild <= size()){
            small = smaller(leftChild, rightChild) ? leftChild : rightChild;
        }
        if(smaller(small, index)){
            Collections.swap(pq, index, small);
            itemMap.put(pq.get(index).getItem(), index);
            itemMap.put(pq.get(small).getItem(), small);
            sink(small);
        }
    }


    @Override
    public int size() {
        return pq.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item))throw new NoSuchElementException();
        int index = itemMap.get(item);
        double old = pq.get(index).getPriority();
        pq.get(index).setPriority(priority);
        if(priority > old){
            sink(index);
        }else {
            swim(index);
        }
    }



    class PriorityNode implements Comparable<PriorityNode>{

        private T item;
        private double priority;

        private PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        private double getPriority() {
            return priority;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            if(o == null){
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)return true;
            if(this.getClass() != obj.getClass()) return false;
            PriorityNode that = (PriorityNode) obj;
            return this.getItem().equals(that.getItem());
        }
    }
}
