package task_1;

import java.util.Arrays;

public class BinaryMinHeap<E> {

    private int lastPosition;//the number of elements in the array
    private E[] array;

    public BinaryMinHeap(int size) {
        this.lastPosition = 0;
        this.array = (E[]) new Object[size];
    }

    public BinaryMinHeap() {
        this.lastPosition = 0;
        this.array = (E[]) new Object[10];
    }
    /** insert an element in the minHeap
     * and re-balance the properties
     * */
    public void insert(E obj){
        // insert it as last
        array[lastPosition] = obj;
        // go down on the depth path to the root as long proprieties are not balanced
        minHeapify(lastPosition);
        lastPosition++;
        // if the array is 80% full, double the size
        if (lastPosition == array.length*4/5){
            array = Arrays.copyOf(array,array.length*2);
        }
    }
    /** add a given input array and convert it to a minHeap
     * */
    public void addMinHeap(E[] arr){
        //copy input
        System.arraycopy(arr, 0, array, 0, arr.length);
        // from last parent down, check/ establish minHeap
        setMinHeap();
    }
    /** convert binary heap to minHeap
     * */
    private void setMinHeap() {
        // parentIndex is the last leaf node parentIndex
        int parentIndex = (array.length - 2) / 2;
        // from last parentIndex down on the depth path to the root
        // convert to minHeap
        while (parentIndex >= 0) { // when parentIndex = 0, the root
            minHeapify(parentIndex, array.length);
            // go to next index(parentIndex) on that level
            parentIndex--;
        }
    }

    /** get the two children, compare them, and if the smallest is smaller than parent
     * swap smallest with parent. after, cal the method again on the new parent(child)
     * */
    private void minHeapify(int parentIndex, int size)
    {
        // get leftChild and rightChild child of node at index parentIndex
        int leftChild = (2*parentIndex) + 1;
        int rightChild = (2*parentIndex) + 2;
        // assume parent is the smallest
        int smallest = parentIndex;
        // compare parent with its leftChild and rightChild to find the value to swap(smallest)
        if (leftChild < size &&   ((Comparable<E>)array[leftChild]).compareTo(array[parentIndex]) < 0) {
            smallest = leftChild;
        }
        if (rightChild < size &&  ((Comparable<E>)array[rightChild]).compareTo(array[smallest]) < 0) {
            smallest = rightChild;
        }
        // if the smallest value is not the parent,
        // swap them and continue the procedure on the child,
        // down on depth path to the root
        if (smallest != parentIndex) {
            swap(parentIndex,smallest);
            minHeapify(smallest, size);
        }
    }
    /** swap two values between the two indexes
     * */
    private void swap(int from, int to){
        E tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    /** if child is smaller than parent,
     * swap them and call the method again on the new parent(child)
     * */
    private void minHeapify(int position){
        // if root, nothing to do
        if(position == 0){
            return;
        }
        int parent = (int) Math.floor((position-1)/2);
        // if child is smaller than parent, swap them and call the method
        // again on the new parent
        if(((Comparable<E>)array[position]).compareTo(array[parent]) < 0){
            swap(position,parent);
            minHeapify(parent);
        }
    }
    public static void main(String[] args) {
        BinaryMinHeap<Integer> insertMinHeap = new BinaryMinHeap<>();
        Integer[] arr = {10,12,1,14,6,5,8,15,3,9,7,4,11,13,2};
        BinaryMinHeap<Integer> liniarTime = new BinaryMinHeap<>(arr.length);
        for (int x: arr){
            insertMinHeap.insert(x);
        }
        liniarTime.addMinHeap(arr);
        System.out.println("Insertion minHeap   "+Arrays.toString(insertMinHeap.array));
        System.out.println("linear-time minHeap "+Arrays.toString(liniarTime.array));
    }
}
