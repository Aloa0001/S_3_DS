package task_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Alex
 */
public class WordPuzzle {
    private int rows;
    private int columns;
    // provides random indexes for filling the map
    private SecureRandom rand = new SecureRandom();
    // supports all possible combinations of the map
    private HashMap<String,String> found = new HashMap<>();
    // supports the right answers
    private HashMap<String,String> answers = new HashMap<>();
    // used for calculation
    private String[] display;
    // supports the random puzzle map
    private String[][] wordPuzzle;
    // read the words.txt
    /** private HashSet<String> words = new HashSet<>();*/
    private Hashtable<String,String> words = new Hashtable<>();
    // alphabet
    private String[] alphabet = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k",
            "l", "z", "x", "c", "v", "b", "n", "m"};

    /**
     * create the board
     * allocate random letters
     * find the words in the puzzle
     * @param edge the number of rows = columns
     * @throws FileNotFoundException read the Words.txt file
     */
    public WordPuzzle(int edge) throws FileNotFoundException {
        this.rows = edge;
        this.columns = edge;
        wordPuzzle = new String[rows][columns];
        display = new String[rows * columns];
        setWordPuzzle();
        fillWordsDB();
        fillAnswers();
    }
    /**
     * populate the puzzle with random letters
     * solve the puzzle
     */
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
    /**
     * according to the size of the puzzle
     * populate the array ONLY with words that fit
     * @throws FileNotFoundException read from Words.txt
     */
    private void fillWordsDB() throws FileNotFoundException {
        File file = new File("src/Words.txt");
        Scanner input = new Scanner(file);
        int count = 0;
        while (input.hasNext()) {
            String word = input.next();
            if (word.length() < rows) {
                words.put(word,word);
                count++;
            }
        }
    }
    /**
     * for each position in the puzzle
     * analyze all possible combinations of letters
     * and add only unique new combinations
     */
    private void findWords() {
        for (int i = 0; i < display.length; i++) {
            findCol(i);
            findDiagonal1(i);
            findDiagonal2(i);
            findRow(i);
        }
    }
    /**
     * check all possibilities on the column
     * @param position to start from, and come back to
     */
    private void findCol(int position) {
        String word = "";
        int count = position;
        int next = 0;
        // go from position  up on the column
        while (count >= 0) {
            next = count;
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count -= rows;
        }
        word = "";
        // go from top back to position
        while (next <= position) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next += rows;
        }
        word = "";
        next = position;
        //go from position to the bottom of the column
        while (next < display.length) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next += rows;
        }
        word = "";
        next -= rows;
        // go from the bottom back to position
        while (next >= position) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next-= rows;
        }
    }
    /**
     * check all possibilities on the row
     * @param position to start from, and come back to
     */
    private void findRow(int position) {
        String word = "";
        int count = position - position % rows;
        int length = count + rows ;
        int next;
        //go from the beginning of the row to position
        while (count <= position) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count++;
        }
        word = "";
        next = position;
         // go from position to the beginning of the row
        while (next > position - position % rows) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next--;
        }
        word = "";
        next = position;
        //go from position to the end of the row
        while (next < length) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next++;
        }
        word = "";
        next--;//length - 1;
        // go from the end of the row back to position
        while (next > position) {
            word = word + display[next];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            next--;
        }
    }
    /**
     * check all possibilities on the first diagonal
     * @param position to start from, and come back to
     */
    private void findDiagonal1(int position) {
        String word = "";
        int count = position;
        int next = position % rows;
        // go up left corner
        while (next > 0 && count >= 0) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count -= (rows + 1);
            next--;
        }
        word = "";
        count += (rows + 1);
        // go back to position from left up corner
        while (count <= position) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count += (rows + 1);
        }
        word = "";
        count = position;
        next = rows - position % rows;
        // go from position to right down corner
        while (next > 0 && count < display.length) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count += (rows + 1);
            next--;
        }
        word = "";
        count -= (rows + 1);
        next = rows - position % rows;
        // go from down right corner to position
        while (next >= 0 && count > 0) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count -= (rows + 1);
            next--;
        }
    }
    /**
     * check all possibilities on the second diagonal
     * @param position to start from, and come back to
     */
    private void findDiagonal2(int position) {
        String word = "";
        int count = position;
        int next = rows - position % rows;
        // got from position to  right up corner
        while (next > 0 && count > 0) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count -= (rows - 1);
            next--;
        }
        word = "";
        count += (rows - 1);
        next = rows - position % rows;
        // go from right up corner to position
        while (next > 0 && count <= position) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count += (rows - 1);
            next--;
        }
        word = "";
        count = position;
        next = position % rows;
        // go from position to left down corner
        while (next > 0 && count < display.length) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count += (rows - 1);
            next--;
        }
        word = "";
        count -= (rows - 1);
        next = position % rows;
        // go from down left corner to position
        while (next > 0 && count < display.length) {
            word = word + display[count];
            if (!found.containsKey(word)) {
                found.put(word,word);
            }
            count -= (rows - 1);
            next--;
        }
    }
    /**
     * compare all combinations with database
     * and select the answer
     */
    private void fillAnswers(){
        for (String x : found.keySet()) {
            if (words.contains(x)) {
                answers.put(x,x);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean integer;
        String word;
        do {
           HashMap<String,String> answer = new HashMap<>();
            int edge = 0;
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("Chose the size of your board edges: ");
                try {
                    integer = false;
                    edge = Integer.parseInt(in.nextLine());
                    System.out.println("\u001B[34m" + "Valid input" + "\u001B[0m");
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[34m" + "input is not an int value" + "\u001B[0m");
                    integer = true;
                }
            } while (integer);
            WordPuzzle wp = new WordPuzzle(edge);
            for (int i = 0; i < edge; i++) {
                for (int j = 0; j < edge; j++) {
                    System.out.print(wp.wordPuzzle[i][j] + "  ");
                }
                System.out.println();
            }
            System.out.println("Enter the words you found one by one\n" +
                    "If you are ready,enter  \"y\"");
            word = "";
            while (!word.equals("y")) {
                word = in.nextLine();
                answer.put(word,word);
            }
            for (String x : answer.keySet()) {
                if (wp.answers.containsKey(x)) {
                    System.out.println("You found " + x + " !");
                    wp.answers.remove(x);
                }
            }
            if (wp.answers.size() > 0) {
                System.out.println("\nSadly, you lost!!! \nyou missed : " + wp.answers.keySet());
            } else {
                System.out.println(" Congratulations !!! \nYou WON THE GAME !!!");
            }
            System.out.println("\nif you want to continue enter \"y\", \nelse enter anything else to quit ");
            word = in.nextLine();
        }while(word.equals("y"));
    }
}
