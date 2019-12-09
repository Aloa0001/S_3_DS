package task_2;

import java.util.LinkedList;

public class HashTable <K,V>{
    /**global variables*/
    LinkedList<HashElement<K,V>>[] hash_array;

    private int numElements, tableSize;
    private double maxLoadFactor;

    public HashTable() {
        this.tableSize = 10;
        maxLoadFactor = 0.75;
        numElements = 0;

        hash_array = (LinkedList<HashElement<K,V>> []) new LinkedList[tableSize];

        for(int i = 0 ; i < tableSize; i++){
            hash_array[i] = new LinkedList<HashElement<K,V>>();
        }
    }
    /**
     * Hash constructor
     * @param tableSize
     */
    public HashTable(int tableSize){
        this.tableSize = tableSize;

        maxLoadFactor = 0.75;
        numElements = 0;

        hash_array = (LinkedList<HashElement<K,V>> []) new LinkedList[tableSize];

        for(int i = 0 ; i < tableSize; i++){
            hash_array[i] = new LinkedList<HashElement<K,V>>();
        }
    }

    /**
     * inner class HashElement
     * @param <K>
     * @param <V>
     */
    class HashElement<K,V> implements Comparable<HashElement<K,V>>{

        K key;
        V value;

        public HashElement(K key,V value){
            this.key = key;
            this.value = value;
        }

        public int compareTo(HashElement<K,V> o){
            return ((Comparable<K>)o.key).compareTo(this.key);
        }

    }
    public boolean add(K key, V value){
        if (loadFactor() > maxLoadFactor) {
            resize(tableSize*2);
        }
        HashElement<K,V> he = new HashElement(key,value);
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF % tableSize;
        hash_array[hashVal].addFirst(he);
        numElements++;
        return true;
    }

    public boolean remove(K key, V value){

        HashElement<K,V> he = new HashElement(key,value);

        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF % tableSize;
        hash_array[hashVal].remove(he);
        numElements--;
        return true;
    }

    public void resize(int newSize){
        LinkedList<HashElement<K,V>> [] newArray = (LinkedList<HashElement<K,V>> [])new LinkedList[newSize];

        for(int i = 0 ; i < newSize ; i ++){
            newArray[i] = new LinkedList<HashElement<K,V>>();
        }
        /*for(K key : this){
            V value = getValue(key);
            HashElement<K,V> he = new HashElement<K,V>(key, value);
            int hashVal = (key.hashCode() & 0x7FFFFFFF)% newSize;
            newArray[hashVal].addFirst(he);
        }*/
        hash_array = newArray;
        tableSize = newSize;
    }

    public double loadFactor(){
        return numElements / tableSize;
    }
}
