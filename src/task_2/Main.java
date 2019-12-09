package task_2;

public class Main {
    public static void main(String[] args) {
        HashTable< Integer, String> hash = new HashTable<>(5);

        hash.add(4371,"dfdsfas");
        hash.add(1323,"dfdsfas");
        hash.add(6173,"dfdsfas");
        hash.add(4199,"dfdsfas");
        hash.add(4344,"dfdsfas");
        hash.add(9679,"dfdsfas");
        hash.add(1989,"dfdsfas");

        System.out.println(hash.toString());
    }
}
