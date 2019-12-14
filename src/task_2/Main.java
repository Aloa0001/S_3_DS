package task_2;


import testTasks.TestMain;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\n******    TEST SEPARATE CHAINING HASHTABLE    ****** \n");
        HashTable.main(null);
        System.out.println("\n\n******           TEST MY HASHTABLE            ******");
        MyHashTable.main(null);
        System.out.println();
        TestMain.main(null);
    }

}
