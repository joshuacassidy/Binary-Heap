public class Heap implements IHeap {

    private int capacity;
    private int size;
    private Integer[] pq;

    public Heap() {
        this.capacity = 10;
        this.size = 0;
        this.pq = new Integer[capacity];
    }

    public Integer peek() {
        return isEmpty() ? null : pq[0];
    }

    public Integer remove() {
        if (isEmpty()) {
            return null;
        } else {
            Integer item = pq[0];
            pq[0] = pq[size-1];
            size--;
            heapifyDown();
            return item;
        }
    }

    public void add(Integer item) {
        resize();
        pq[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size-1;
        while (hasParent(index) && getParent(index) > pq[index]) {
            swap(getParent(index), index);
            index = getParent(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = 2*index+1;
            if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallerChildIndex = 2*index+2;
            }

            if(pq[index] < pq[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;

        }
    }


    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public boolean isEmpty() {
        return size==0;
    }

    private void resize() {
        if (size == capacity) {
            Integer[] temp = pq;
            capacity*=2;
            pq = new Integer[capacity];
            for (int i = 0; i < temp.length; i++) {
                pq[i]=temp[i];
            }
        }
    }

    private int getLeftChild(int index) {
        return pq[2*index+1];
    }

    private int getRightChild(int index) {
        return pq[2*index+2];
    }

    private int getParent(int index) {
        return pq[(index - 1)/2];
    }

    private boolean hasLeftChild(int index) {
        return (2 * index + 1) < size;
    }

    private boolean hasRightChild(int index) {
        return (2 * index + 2) < size;
    }

    private boolean hasParent(int index) {
        return ((index - 1)/2) < size;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < pq.length; i++) {
            str+=i == pq.length-1 ? pq[i]+"]" : pq[i]+", ";
        }
        return str;
    }

}
