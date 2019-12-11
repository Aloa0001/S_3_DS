package task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class WordPuzzle {
    private SecureRandom rand;
    private ArrayList<String> found;
    private ArrayList<String> answers;
    private String[] display;
    private String[][] wordPuzzle;
    private int rows;
    private int columns;
    private ArrayList words; // stores the db
    String[] alphabet = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k",
            "l", "z", "x", "c", "v", "b", "n", "m"};

    public WordPuzzle(int edge) throws FileNotFoundException {
        this.rows = edge;
        this.columns = edge;
        wordPuzzle = new String[rows][columns];
        found = new ArrayList<>();
        answers = new ArrayList<>();
        rand = new SecureRandom();
        words = new ArrayList();
        display = new String[rows * columns];
        setWordPuzzle();
        fillWordsDB();
    }

    private void setWordPuzzle() {
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                display[index] = wordPuzzle[i][j] = alphabet[rand.nextInt(26)];
                index++;
            }
        }
        findWords();
    }

    private void fillWordsDB() throws FileNotFoundException {
        File file = new File("src/Words.txt");
        Scanner input = new Scanner(file);
        int count = 0;
        while (input.hasNext()) {
            String word = input.next();
            if (word.length() < rows) {
                words.add(word);
                count++;
            }
        }
    }

    private void findWords() {
        for (int i = 0; i < display.length; i++) {
            findCol(i);
            findDiagonal(i);
            findDiagonal2(i);
            findRow(i);
        }
    }

    private void findCol(int position) {
        String word = "";
        int count = position;
        int next = 0;
        while (count >= 0) {
            next = count;
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            count -= rows;
        }
        word = "";
        while (next <= position) {
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            next += rows;
        }
    }

    private void findRow(int position) {
        String word = "";
        int count = position - position % rows;
        int next = 0;
        while (count <= position) {
            next = count;
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            count++;
        }
        word = "";
        while (next >= position - position % rows) {
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            next--;
        }
    }

    private void findDiagonal(int position) {
        String word = "";
        int count = position;
        int next = 0;
        while (count >= 0) {
            next = count;
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            count -= (rows + 1);
        }
        word = "";
        while (next <= position) {
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            next += (rows + 1);
        }
    }

    private void findDiagonal2(int position) {
        String word = "";
        int count = position;
        int next = 0;
        while (count >= 0) {
            next = count;
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            count -= (rows - 1);
        }
        word = "";
        while (next <= position) {
            word = word + display[next];
            if (!found.contains(word)) {
                found.add(word);
            }
            next += (rows - 1);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Chose the size of your ");
//        int edge = 10;
//        WordPuzzle wp = new WordPuzzle(edge);
//        for (int i = 0; i < edge; i++) {
//            for (int j = 0; j < edge; j++) {
//                System.out.print(wp.wordPuzzle[i][j] + "  ");
//            }
//            System.out.println();
//        }
//        wp.findWords();
//
//        for (String x : wp.found) {
//            if (wp.words.contains(x)) {
//                System.out.println(x);
//            }
//        }
    }
}
