package task_2;

import java.util.Arrays;

public class MyHashTable {

    int[] array;
    double maxLoadFactor;
    double numberOfElements;

    public MyHashTable() {
        array = new int[10];
        Arrays.fill(array, 0);
        maxLoadFactor = 0.75;
    }

    public MyHashTable(int size) {
        array = new int[size];
        Arrays.fill(array, 0);
        maxLoadFactor = 0.5;
    }


    /**********************************/

    /**
     * LINEAR PROBING IMPLEMENTATION
     */
    public void addLinear(int value) {
        System.out.println(loadFactor());
        if (loadFactor() > maxLoadFactor) {
            resize(array.length * 2);
        }
        int i = 0;
        int index = hashFunctionLinear(value, i);
        while (array[index] != 0) {
            index = hashFunctionLinear(value, ++i);
            if (i > 3) {
                resize(array.length * 2);
            }
        }
        array[index] = value;
        numberOfElements++;
    }

    public int hashFunctionLinear(int key, int i) {
        return (key + collisionResolutionLinearPolicy(i)) % 10;
    }

    public void resize(int newSize) {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHash(arr);
    }

    public void reHash(int[] arr) {
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            int newIndex = hashFunctionLinear(arr[i], j);
            while (array[newIndex] != 0){
                newIndex = hashFunctionLinear(arr[i], ++j);
            }
            array[newIndex] = arr[i];
        }
    }
    /*******************************************/


    /**
     * QUADRATIC PROBING IMPLEMENTATION
     */
    public boolean addQuadratic(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeQuadratic(array.length * 2);
        }
        int i = 0;
        int index = hashFunctionQuadratic(value, i);
        while (array[index] != 0) {
            index = hashFunctionQuadratic(value, ++i);
            if (i > 3) {
                resizeQuadratic(array.length * 2);
            }
        }
        array[index] = value;
        numberOfElements++;
        return true;
    }

    public int hashFunctionQuadratic(int key, int i) {
        return (key + collisionResolutionQuadraticPolicy(i)) % 10;
    }

    public void resizeQuadratic(int newSize) {
        int[] arr = Arrays.copyOf(array,array.length+1);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashQuadratic(arr);
    }

    public void reHashQuadratic(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            int newIndex = hashFunctionQuadratic(arr[i], j);
            while (array[newIndex] != 0){
                newIndex = hashFunctionLinear(arr[i], ++j);
            }
            array[newIndex] = arr[i];
        }
    }
    /*******************************************/

    /**
     * FUNCTION 2 LINEAR IMPLEMENTATION
     */
    public boolean addLinearH2(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeH2(array.length * 2);
        }
        int i = 0;
        int index = hashF2Linear(value, i);
        while (array[index] != 0) {
            index = hashF2Linear(value, ++i);
            if (i > 3) {
                resizeH2(array.length * 2);
            }
        }
        array[index] = value;
        numberOfElements++;
        return true;
    }

    public int hashF2Linear(int key, int i) {
        return 7 - (key + collisionResolutionLinearPolicy(i)) % 7;
    }
    public void resizeH2(int newSize) {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashH2(arr);
    }

    public void reHashH2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            int newIndex = hashF2Linear(arr[i], j);
            while (array[newIndex] != 0){
                newIndex = hashFunctionLinear(arr[i], ++j);
            }
            array[newIndex] = arr[i];
        }
    }
    /********************************************/

    /**
     * FUNCTION 2 QUADRATIC IMPLEMENTATION
     */
    public boolean addQuadraticH2(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeQuadratic(array.length * 2);
        }
        int i = 0;
        int index = hashF2Quadratic(value, i);
        while (array[index] != 0) {
            index = hashF2Quadratic(value, ++i);
            if (i > 3) {
                resizeQuadraticH2(array.length * 2);
            }
        }
        array[index] = value;
        numberOfElements++;
        return true;
    }

    public int hashF2Quadratic(int key, int i) {
        return 7 - (key + collisionResolutionQuadraticPolicy(i)) % 7;
    }
    public void resizeQuadraticH2(int newSize) {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashQuadraticH2(arr);
    }

    public void reHashQuadraticH2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            int newIndex = hashF2Quadratic(arr[i], j);
            while (array[newIndex] != 0){
                newIndex = hashFunctionLinear(arr[i], ++j);
            }
            array[newIndex] = arr[i];
        }
    }
    /********************************************/


    /**
     * COLLISION RESOLUTION POLICIES
     */
    public int collisionResolutionLinearPolicy(int i) {
        return i;
    }

    public int collisionResolutionQuadraticPolicy(int i) {
        return (int) Math.pow(i, 2);
    }

    public double loadFactor() {
        return numberOfElements/array.length;
    }

    /**
     * TEST DRIVER
     *
     * @param args .
     */
    public static void main(String[] args) {
        MyHashTable hashLinearProbing = new MyHashTable();
        MyHashTable hashQuadraticProbing = new MyHashTable();
        MyHashTable hashLinearProbingF2 = new MyHashTable();
        MyHashTable f2Rehash = new MyHashTable(10);
        MyHashTable hashQuadraticProbingF2 = new MyHashTable();
        int[] arr = {4371, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int x : arr) {
            hashLinearProbing.addLinear(x);
            hashQuadraticProbing.addQuadratic(x);
            hashLinearProbingF2.addLinearH2(x);
            hashQuadraticProbingF2.addQuadraticH2(x);
            f2Rehash.addLinearH2(x);
        }

        /** LINEAR PROBING */
        System.out.println("LINEAR PROBING for :  "
                + Arrays.toString(arr));
        System.out.println(Arrays.toString(hashLinearProbing.array));

        /** QUADRATIC PROBING */
        System.out.println("\nQUADRATIC PROBING for :  "
                + Arrays.toString(arr));
        System.out.println(Arrays.toString(hashQuadraticProbing.array));

        /** F2 ___ LINEAR PROBING */
        System.out.println("\nF2 ___ LINEAR PROBING for :  "
                + Arrays.toString(arr));
        System.out.println(Arrays.toString(hashLinearProbingF2.array));

        /** F2 ___after resized to double and re-hashing*/
        System.out.println("\nF2___ after resized to double and rehashing :\n"
                +Arrays.toString(f2Rehash.array)+
                "\nThe resize+rehash method was triggered by the load_factor set " +
                "to 0.5, which was pass with the 6th element  ");

        /** F2 ___ LINEAR PROBING */
        System.out.println("\nF2 ___ QUADRATIC PROBING for :  "
                + Arrays.toString(arr));
        System.out.println(Arrays.toString(hashQuadraticProbingF2.array));
    }
}
