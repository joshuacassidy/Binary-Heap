public class Main {

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.remove();
        heap.remove();
        heap.add(4);
        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(1);
        System.out.println(heap);
    }

}
