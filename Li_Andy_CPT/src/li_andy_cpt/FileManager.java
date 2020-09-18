/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class records the scores onto a file and then reads and
 * sorts the file using recursion and quick sort to determine the highest score.
 */
package li_andy_cpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private final File file;
    private FileWriter fw;
    private PrintWriter pw;
    private FileReader fr;
    private Scanner s;
    private final ArrayList<Integer> scores = new ArrayList<>();
    private int[] score;

    public FileManager(String filename) {
        this.file = new File(filename); //created File object
    }

    public int getHighScore() {
        readFromFile();
        quickSort(score, 0, score.length - 1);
        return score[score.length - 1];
    }

    /**
     * Description: this method writes to a file using File Writer and Print
     * Writer
     *
     * pre condition: the file must be valid
     *
     * post condition: the score is added to the file
     *
     * @param score - this is the score of the game
     * @param append - allows the program to continuously add scores without
     * clearly the file so when the user closes the program and reopens it, the
     * highest score will still be saved
     */
    public void writeToFile(int score, boolean append) {
        try {
            fw = new FileWriter(file, append);
            pw = new PrintWriter(fw);
            pw.println(score);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error occurred with FileWriter");
        }
    }

    /**
     * Description: this method reads from a file using File Reader and converts
     * the contents of the file into an array
     *
     * pre condition: file name must be valid and array list must be
     * instantiated
     *
     * post condition: the contents of the file will be placed into an array
     */
    public void readFromFile() {
        try {
            fr = new FileReader(file);
            s = new Scanner(fr);
            while (s.hasNextLine()) {
                int score = s.nextInt();
                scores.add(score);
                s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred with FileReader");
        }
        score = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            score[i] = scores.get(i);
        }
    }

    /**
     * Description: this method determines uses recursion through quick sort in
     * order to sort the score in ascending order so the final value can be
     * extracted as the highest score
     *
     * precondition: array has more than 1 element
     *
     * post condition: array is sorted
     *
     * @param arr - this is the unsorted array
     * @param begin - this is the index of the first element in the array
     * @param end - this is the index of the last element in the array
     */
    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    /**
     * Description: this method sorts the array using quick sort
     *
     * precondition: array has more than 1 element
     *
     * post condition: array is sorted
     *
     * @param arr - this is the unsorted array
     * @param begin - this is the index of the first element in the array
     * @param end - this is the index of the last element in the array
     */
    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    @Override
    public String toString() {
        return scores.toString();
    }
}
