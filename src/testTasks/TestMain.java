package testTasks;

import task_1.BinaryMinHeap;
import task_2.Main;
import task_3.TestRunTimeMain;
import task_3.WordPuzzleArrayImpl;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {
    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws FileNotFoundException {
        seminarMenu();
    }

    private static void seminarMenu() throws FileNotFoundException {
        testTask(chooseTaskToTest());
    }

    private static void testTask(int task) throws FileNotFoundException {
        if (task == 1) {
            //test task
            BinaryMinHeap.main(null);
        }
        if (task == 2) {
            //test task
            Main.main(null);
        }
        if (task == 3) {
            //test task
            chooseTestResultsOrPlay();
        }
        if (task == 5) {
            System.exit(0);
        }

    }

    private static int chooseTaskToTest() {
        String[] answers = {"1", "2", "3", "5"};
        String answer;
        boolean condition = false;
        do {
            System.out.println("\u001B[31m" + "Please enter the task you want to test: \npress 1 for task1\npress 2 for task2\n" +
                    "press 3 for task3\n\npress 5 to exit" + "\u001B[0m");
            answer = in.nextLine();
            for (String x : answers) {
                if (x.equals(answer)) {
                    condition = true;
                    break;
                }
            }
        } while (!condition);
        return Integer.parseInt(answer);
    }

    private static void chooseTestResultsOrPlay() throws FileNotFoundException {
        System.out.println("WORD PUZZLE");
        String[] answers = {"1", "2", "5"};
        String answer;
        boolean condition = false;
        do {
            System.out.println("\u001B[31m" + "Please enter the task you want to test: \npress 1 to play WORD PUZZLE" +
                    "\npress 2 to see tests results\n\npress 5 to exit" + "\u001B[0m");
            answer = in.nextLine();
            for (String x : answers) {
                if (x.equals(answer)) {
                    condition = true;
                    break;
                }
            }
        } while (!condition);
        if (answer.equals("1")) {
            //play
            System.out.println("WORD PUZZLE");
            WordPuzzleArrayImpl.main(null);
        }
        if (answer.equals("2")) {
            //test results
            TestRunTimeMain.main(null);
        }
        if (answer.equals("5")) {
            //test task
            chooseTaskToTest();
        }
    }

}
