
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static String fileDestination = "empty";
    static List<List<String>> records = new ArrayList<>();
    public static String line = " ";

    public static void main(String[] args) throws FileNotFoundException {
        // taking the file destination from user
        fileDestination = args[0];
        // checking if there has been a problem about taking the fileDestination
        if (fileDestination.equals("empty")) {

            System.out.println("File Destination unknown please try again.");

        }
        System.out.println("this works");
        readCSVFile(fileDestination);
        printArrayListToConsole(records);
        System.out.println("this works");
    }

    // to read our file and parsing its values to an arraylist
    public static void readCSVFile(String destination) throws FileNotFoundException {
        // using try catch to prevent the program from crashing
        try {
            // buffered reader reads the input from our file
            BufferedReader br = new BufferedReader(new FileReader(destination));
            // creating a while loop to continue when there is a next element to read
            while ((line = br.readLine()) != null) {
                // splitting lines
                String[] values = line.split(",");
                // adding read elements to our array
                records.add(Arrays.asList(values));
            }
            // closing the bufferedReader to save space in memory
            br.close();

            // using catch to better understand if there has been a problem about our code
        } catch (Exception e) {
            System.out.println("an error has ben occured please try again.");

        }

    }

    // to print the arraylist to our console
    public static void printArrayListToConsole(List<List<String>> array) {
        //using for each to iterate amongs all elements of our array.
        for (List<String> list : array) {

            System.out.println(list);

        }
    }
}