package task_2;

import java.util.Arrays;
import java.util.LinkedList;


public class HashTable {

    private LinkedList<Integer>[] hash_array;
    private int numElements;
    private int tableSize;
    private double maxLoadFactor;
    double indexCounter;

    public HashTable() {
        this.tableSize = 10;
        maxLoadFactor = 0.75;
        numElements = 0;
        indexCounter = 0;
        hash_array = (LinkedList<Integer> []) new LinkedList[tableSize];
        for(int i = 0 ; i < tableSize; i++){
            hash_array[i] = new LinkedList<>();
        }
    }
    /**
     * Hash constructor
     * @param size .
     */
    public HashTable(int size){
        this.tableSize = size;
        maxLoadFactor = 0.75;
        numElements = 0;
        indexCounter = 0;
        hash_array = (LinkedList<Integer> []) new LinkedList[size];
        for(int i = 0 ; i < size; i++){
            hash_array[i] = new LinkedList<>();
        }
    }

    public void add(int value){
        if (loadFactor() > maxLoadFactor) {
            resize();
        }
        int i = 0;
        int hashVal = value%10 + i;
        hash_array[hashVal].addLast(value);
        if (hash_array[hashVal] == null){
            indexCounter++;
        }
        numElements++;
    }

    public void resize(){
        LinkedList<Integer> [] newArray = (LinkedList<Integer> [])new LinkedList[hash_array.length*2];
        for(int i = 0 ; i < hash_array.length*2 ; i ++){
            newArray[i] = new LinkedList<>();
        }

        hash_array = newArray;
        tableSize = hash_array.length;
    }


    public double loadFactor(){
        return indexCounter / tableSize;
    }

    public static void main(String[] args) {
        HashTable hash = new HashTable();
        int[] arr = {4371, 1323, 6173, 4199, 4344, 9679, 1989};
        for (int x : arr) {
            hash.add(x);
        }
        System.out.println("Input : "+"  "+ Arrays.toString(arr));
        System.out.println("Separate chaining : "+"  "+ Arrays.toString(hash.hash_array));
    }
}
