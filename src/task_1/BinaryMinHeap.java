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
     * read the heap in the array order
     * */
    private void linearTime() {
        for (E e : array) {
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

    public static int findHeight(int nodes) {
        if (nodes == 1) {
            return nodes - 1;
        }
        int count = 1;
        int level = 1;
        while (count <= nodes) {
            count = count + (int) Math.pow(2, level);
            level++;
        }
        return level - 1;
    }

    public static int findLevel(int node) {
        if (node == 0) {
            return 0;
        }
        int count = 1;
        int level = 1;
        while (count <= node) {
            count = count + (int) Math.pow(2, level);
            level++;
        }
        return level - 1;
    }

    public static int[] returnMinNode(int levels) {
        int[] minNodes = new int[levels + 1];
        int level = 0;
        while (level <= levels) {
            minNodes[level] = (int) Math.pow(2, level) - 1;
            level++;
        }
        return minNodes;
    }

    public static int[] returnMaxNode(int levels) {
        int[] maxNodes = new int[levels + 1];
        int level = 0;
        while (level <= levels) {
            maxNodes[level] = (int) Math.pow(2, level + 1) - 2;
            level++;
        }
        return maxNodes;
    }

    public static void main(String[] args) {
        BinaryMinHeap<Integer> insertMinHeap = new BinaryMinHeap<>();
        Integer[] arr = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
        BinaryMinHeap<Integer> linearTime = new BinaryMinHeap<>(arr.length);
        for (int x : arr) {
            insertMinHeap.insert(x);
        }
        linearTime.addMinHeap(arr);

        System.out.println("In order: ");
        insertMinHeap.inOrder();
        System.out.println();
        linearTime.inOrder();

        System.out.println("Pre-Order Traversal: ");
        insertMinHeap.preOrder();
        System.out.println();
        linearTime.preOrder();

        System.out.println("Post-Order Traversal: ");
        insertMinHeap.postOrder();
        System.out.println();
        linearTime.postOrder();

        System.out.println("Linear-Time Traversal: ");
        insertMinHeap.linearTime();
        System.out.println();
        linearTime.linearTime();

        System.out.println("\nInsertion minHeap   " + Arrays.toString(insertMinHeap.array));
        System.out.println("linear-time minHeap " + Arrays.toString(linearTime.array));
        System.out.println("Height for 33 nodes heap is " + Arrays.toString(returnMinNode(findHeight(1023))));
        System.out.println("Height for 33 nodes heap is " + Arrays.toString(returnMaxNode(findHeight(1023))));
        System.out.println("level for 4 node is " + (findLevel(2)));
    }
}
