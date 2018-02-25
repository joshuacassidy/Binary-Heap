public class Heap implements IHeap {

    private int capacity;
    private int size;
    private Integer[] heap;

    public Heap() {
        this.capacity = 10;
        this.size = 0;
        this.heap = new Integer[capacity];
    }

    public Integer peek() {
        return isEmpty() ? null : heap[0];
    }

    public Integer remove() {
        if (isEmpty()) {
            return null;
        } else {
            Integer item = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown();
            return item;
        }
    }

    public void add(Integer item) {
        resize();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size-1;
        while (hasParent(index) && getParent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftIndex(index);
            if (hasRightChild(index) && getRight(index) < getLeft(index)) {
                smallerChildIndex = getRightIndex(index);
            }

            if(heap[index] != null && heap[index] < heap[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;

        }
    }



    public int getLeftIndex(int index) {
        return 2 * index +1;
    }

    public int getRightIndex(int index) {
        return 2 * index +2;
    }


    private void swap(int i, int j) {
        Integer temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty() {
        return size==0;
    }

    private void resize() {
        if (size == capacity) {
            Integer[] temp = heap;
            capacity*=2;
            heap = new Integer[capacity];
            for (int i = 0; i < temp.length; i++) {
                heap[i]=temp[i];
            }
        }
    }

    private int getLeft(int index) {
        return heap[2*index+1];
    }

    private int getRight(int index) {
        return heap[2*index+2];
    }

    private int getParent(int index) {
        return heap[(index - 1)/2];
    }

    private int getParentIndex(int index) {
        return (index - 1)/2;
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
        for (int i = 0; i < size; i++) {
            str+=i == size-1 ? heap[i]+"]" : heap[i]+", ";
        }
        return str;
    }

}
