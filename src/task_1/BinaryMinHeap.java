package task_1;

import testTasks.TestMain;
import utilities.MyTimer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

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

    /**
     * insert an element in the minHeap
     * and re-balance the properties
     */
    public void insert(E obj) {
        // insert it as last
        array[lastPosition] = obj;
        // go down on the depth path to the root as long proprieties are not balanced
        minHeapify(lastPosition);
        lastPosition++;
        // if the array is 50% full, double the size
        if (lastPosition == array.length / 2) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * add a given input array and convert it to a minHeap
     */
    public void addMinHeap(E[] arr) {
        //copy input
        System.arraycopy(arr, 0, array, 0, arr.length);
        // from last parent down, check/ establish minHeap
        setMinHeap();
        lastPosition = arr.length;
        array = Arrays.copyOf(array, array.length * 5 / 2); // give space for null leaves to be used in traversals
    }

    /**
     * convert binary heap to minHeap
     */
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

    /**
     * get the two children, compare them, and if the smallest is smaller than parent
     * swap smallest with parent. after, cal the method again on the new parent(child)
     */
    private void minHeapify(int parentIndex, int size) {
        // get leftChild and rightChild child of node at index parentIndex
        int leftChild = (2 * parentIndex) + 1;
        int rightChild = (2 * parentIndex) + 2;
        // assume parent is the smallest
        int smallest = parentIndex;
        // compare parent with its leftChild and rightChild to find the value to swap(smallest)
        if (leftChild < size && ((Comparable<E>) array[leftChild]).compareTo(array[parentIndex]) < 0) {
            smallest = leftChild;
        }
        if (rightChild < size && ((Comparable<E>) array[rightChild]).compareTo(array[smallest]) < 0) {
            smallest = rightChild;
        }
        // if the smallest value is not the parent,
        // swap them and continue the procedure on the child,
        // down on depth path to the root
        if (smallest != parentIndex) {
            swap(parentIndex, smallest);
            minHeapify(smallest, size);
        }
    }

    /**
     * swap two values between the two indexes
     */
    private void swap(int from, int to) {
        E tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    /**
     * if child is smaller than parent,
     * swap them and call the method again on the new parent(child)
     */
    private void minHeapify(int position) {
        // if root, nothing to do
        if (position == 0) {
            return;
        }
        int parent = (int) Math.floor((position - 1) / 2);
        // if child is smaller than parent, swap them and call the method
        // again on the new parent
        if (((Comparable<E>) array[position]).compareTo(array[parent]) < 0) {
            swap(position, parent);
            minHeapify(parent);
        }
    }
    /**
     * remove root, bring last as root,
     * and trickle down picking smallest between children
     */
    private E remove(){
        E tmp = array[0];
        swap(0,--lastPosition);
        array[lastPosition] = null;
        trickleDown(0);
        array = Arrays.copyOfRange(array,0,array.length);
        return tmp;
    }
    /**
     * starting with the given index, compare children
     * and swap smallest with parent recursively
     * as long parent is bigger
     * */
    public void trickleDown(int parent){
        int leftChild = 2*parent+1;
        int rightChild = 2*parent +2;
        int smallest;
        // pick smallest of the two children
        if (array[leftChild] == null && array[rightChild] != null){
            // if only left available case
            smallest = rightChild;
        }else if (array[rightChild] == null && array[leftChild] != null) {
            // if only right available case
            smallest = leftChild;
        }else if (array[leftChild] == null && array[rightChild] == null){
            // leaves level
            return;
        }else if ((((Comparable<E>)array[leftChild]).compareTo(array[rightChild]) < 0)){
            smallest = leftChild;
        }else{
            smallest = rightChild;
        }
        // if the parent is smaller than the smallest child, stop
        if ((((Comparable<E>)array[parent]).compareTo(array[smallest]) < 0)) {
            return;
        }
        // if the parent is bigger than the smallest child, swap
        swap(parent, smallest);
        // recursive call on the new index of the initial value
        trickleDown(smallest);
    }
    /**
     * read the heap in the array order
     * */
    private void linearTime() {
        for (E e : array) {
            if (e != null)
                System.out.print(e + ", ");
        }
        System.out.println(); // jump to next line
    }
    /**
     * call pre-order traversal
     * */
    private void preOrder() {
        preOrderTraversal(0);
        System.out.println(); // jump to next line
    }
    /**
     * call heap in post-order traversal
     * */
    private void postOrder() {
        postOrderTraversal(0);
        System.out.println(); // jump to next line
    }
    /**
     * call in order traversal
     * */
    private void inOrder() {
        inOrderTraversal(0);
        System.out.println(); // jump to next line
    }
    /**
     * read the heap in-order traversal recursively
     * leftChild -> parent -> rightChild
     * start from leftmost child
     * */
    private void inOrderTraversal(int index) {
        // go through the graph as long as has values
        if (array[index] == null) {
            return;
        }
        //call recursively the method on left child
        inOrderTraversal(2 * index + 1);
        // print the node
        System.out.print(array[index] + ", ");
        //call recursively the method on right child
        inOrderTraversal(2 * index + 2);
    }
    /**
     * read the heap post-order traversal recursively
     * leftChild -> rightChild -> parent
     * start from leftmost leaf
     * */
    private void postOrderTraversal(int index) {
        if (array[index] == null) {
            return;
        }
        //call recursively the method on left child
        postOrderTraversal(2 * index + 1);
        //call recursively the method on right child
        postOrderTraversal(2 * index + 2);
        // print the node
        System.out.print(array[index] + ", ");
    }
    /**
     * read the heap pre-order traversal recursively
     *  parent -> leftChild -> rightChild
     * start from root
     * */
    private void preOrderTraversal(int index) {
        if (array[index] == null) {
            return;
        }
        // print the node
        System.out.print(array[index] + ", ");
        //call recursively the method on left child
        preOrderTraversal(2 * index + 1);
        //call recursively the method on right child
        preOrderTraversal(2 * index + 2);
    }

    public static void main(String[] args) throws FileNotFoundException {

        MyTimer timer = new MyTimer();
        int[] arr1000000 = new int[1000000];
        int[] arr10000;
        int[] arr1000;
        int[] arr100;
        Integer[] arR100 = new Integer[100];
        Integer[] arR1000 = new Integer[1000];
        Integer[] arR10000 = new Integer[10000];

        try {
            arr1000000 = Files.lines(Paths.get("src/Numbers.txt")).mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arr10000 = Arrays.copyOf(arr1000000, 10000);
        arr1000 = Arrays.copyOf(arr1000000, 1000);
        arr100 = Arrays.copyOf(arr1000000, 100);

        for (int i = 0; i < 100; i++){
            arR100[i] = arr1000000[i];
        }
        for (int i = 0; i < 1000; i++){
            arR1000[i] = arr1000000[i];
        }
        for (int i = 0; i < 10000; i++){
            arR10000[i] = arr1000000[i];
        }

        BinaryMinHeap<Integer> insertMinHeap = new BinaryMinHeap<>();
        BinaryMinHeap<Integer> insertMinHeap100 = new BinaryMinHeap<>();
        BinaryMinHeap<Integer> insertMinHeap1000 = new BinaryMinHeap<>();
        BinaryMinHeap<Integer> insertMinHeap10000 = new BinaryMinHeap<>();
        BinaryMinHeap<Integer> linearTime100 = new BinaryMinHeap<>(100);
        BinaryMinHeap<Integer> linearTime1000 = new BinaryMinHeap<>(1000);
        BinaryMinHeap<Integer> linearTime10000 = new BinaryMinHeap<>(10000);
        Integer[] arr = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
        BinaryMinHeap<Integer> linearTime = new BinaryMinHeap<>(arr.length);

        /**test insert***************************************************/
        timer.startTimer();
        for (int x : arr100) {
            insertMinHeap100.insert(x);
        }
        System.out.println("\n\nTask 1d.  RUNTIME FOR INSERTION");
        timer.stopTimer();
        System.out.println("RunTime using insertion for 100 is: "+timer.getTime());
        timer.startTimer();
        for (int x : arr1000) {
            insertMinHeap1000.insert(x);
        }
        timer.stopTimer();
        System.out.println("RunTime using insertion for 1000 is: "+timer.getTime());
        timer.startTimer();
        for (int x : arr10000) {
            insertMinHeap10000.insert(x);
        }
        timer.stopTimer();
        System.out.println("RunTime using insertion for 10000 is: "+timer.getTime());
        /*****************************************************************/

        /** test add linear-time******************************************/
        System.out.println("\n\n1d.  RUNTIME FOR LINEAR-TIME ");

        timer.startTimer();
        linearTime100.addMinHeap(arR100);
        timer.stopTimer();
        System.out.println("Time for linear-time 100 is: "+timer.getTime());
        timer.startTimer();
        linearTime1000.addMinHeap(arR1000);
        timer.stopTimer();
        System.out.println("Time for linear-time1000 is: "+timer.getTime());
        timer.startTimer();
        linearTime10000.addMinHeap(arR10000);
        timer.stopTimer();
        System.out.println("Time for linear-time 10000 is: "+timer.getTime());
        /******************************************************************/

        for (int x : arr) {
            insertMinHeap.insert(x);
        }
        linearTime.addMinHeap(arr);
        System.out.println("\n\nTask 1c. TRAVERSING THE TREES:  ");
        System.out.println("\nIn-Order Traversal, insertionMinHeap: ");
        insertMinHeap.inOrder();
        System.out.println("\nIn-Order Traversal, linear-time minHeap: ");
        linearTime.inOrder();

        System.out.println("\nPre-Order Traversal, insertionMinHeap: ");
        insertMinHeap.preOrder();
        System.out.println("\nPre-Order Traversal, linear-time minHeap: ");
        linearTime.preOrder();

        System.out.println("\nPost-Order Traversal, insertionMinHeap: ");
        insertMinHeap.postOrder();
        System.out.println("\nPost-Order Traversal, linear-time minHeap: ");
        linearTime.postOrder();

        System.out.println("\nLinear-Time Traversal, insertionMinHeap: ");
        insertMinHeap.linearTime();
        System.out.println("\nLinear-Time Traversal, linear-time minHeap: ");
        linearTime.linearTime();

        /**  1 A,B,E*/
        System.out.println("\n\nTask 1 a, b, e, :");
        System.out.println("\nSeminar example Insertion minHeap   " + Arrays.toString(insertMinHeap.array));
        System.out.println("Seminar example linear-time minHeap " + Arrays.toString(linearTime.array));
        System.out.println("After removing: ");
        System.out.println("Remove root from linear: "+linearTime.remove());
        System.out.println("Seminar example linear-time minHeap " + Arrays.toString(linearTime.array));
        System.out.println("Remove root from insertion heap: "+insertMinHeap.remove());
        System.out.println("Seminar example Insertion minHeap   " + Arrays.toString(insertMinHeap.array));

        System.out.println("\n\nComparison for add/remove operations between insertion and \nlinear-time minHeaps, having the same elements\n  ");
        timer.startTimer();
        for (int x: arr){
            linearTime100.insert(x);
        }
        timer.stopTimer();
        System.out.println("Average Linear time adding: "+timer.getTime());

        timer.startTimer();
        for (int i = 0; i < 10; i++){
            linearTime100.remove();
        }
        timer.stopTimer();
        System.out.println("Average Linear time removing "+timer.getTime());

        timer.startTimer();
        for (int x: arr){
            insertMinHeap100.insert(x);
        }
        timer.stopTimer();
        System.out.println("Average Insertion time adding "+timer.getTime());

        timer.startTimer();
        for (int i = 0; i < 10; i++){
            insertMinHeap100.remove();
        }
        timer.stopTimer();
        System.out.println("Average insertion time removing "+timer.getTime()+"\n\n");
        TestMain.main(null);
    }
}
