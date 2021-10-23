import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static String fileDestination = "empty";
    static List<List<String>> dataHolder = new ArrayList<>();
    public static String line = " ";
    public static long lineCount = 0;
    public static long momentaryRowCount = 0;
    public static long rowControlNumber = 0;
    public static int insideDataCount = 0;
    public static int borderDataCount = 0;
    public static int emptyDataCount = 0;
    public static String[] missingDataPointer = new String[1000];

    public static void main(String[] args) throws FileNotFoundException {

        // taking the file destination from user
        fileDestination = args[0];

        // checking if there has been a problem about taking the fileDestination
        if (fileDestination.equals("empty")) {
            // printing and error message to our user
            System.out.println("File Destination unknown please try again.");

        }

        /* System.out.println("this works"); */
        initArray(missingDataPointer);
        /*
         * System.out.println("line count;" + lineCount);
         * System.out.println("row count;" + rowControlNumber);
         * System.out.println("data count;" + insideDataCount);
         * System.out.println("emptydata;" + emptyDataCount);
         */
        /* long startTime = System.currentTimeMillis(); */
        readCSVFile(fileDestination);
        /*
         * long endTime = System.currentTimeMillis();
         * System.out.println("time elapsed ms:" + (endTime - startTime));
         * System.out.println("line count;" + lineCount);
         * System.out.println("row count;" + rowControlNumber);
         * System.out.println("data count;" + insideDataCount);
         * System.out.println("border data count;" + borderDataCount);
         * System.out.println("emptydata;" + emptyDataCount);
         */
        
        
         /*printArrayListToConsole(dataHolder);*/
        
        
        /*
         * System.out.println("------");
         * 
         * for (String string : cleanupMissingDataPointer(missingDataPointer)) {
         * 
         * System.out.println(string);
         * 
         * }
         */
        printMissingDataToTXT(cleanupMissingDataPointer(missingDataPointer));
        /* System.out.println("this works"); */
        System.out.println("Success!");
    }

    // to read our file and parsing its values to an arraylist
    public static void readCSVFile(String destination) throws FileNotFoundException {
        // to know the index of missing data
        int missingDataIndex = 0;
        // using try catch to prevent the program from crashing
        try {

            // buffered reader reads the input from our file
            BufferedReader br = new BufferedReader(new FileReader(destination));

            // creating a while loop to continue when there is a next element to read
            while ((line = br.readLine()) != null) {

                // taking the linecount to use after to print into a new file
                lineCount++;

                // splitting data with commas
                String[] values = line.split(",");

                // looking for missing data spots
                for (String data : values) {

                    // counting the rows, counting the data
                    momentaryRowCount++;
                    insideDataCount++;

                    // checking if the data spot is empty
                    if (data.equals("")) {

                        // saving the missing inside data spots' index
                        missingDataPointer[missingDataIndex] = lineCount + "," + momentaryRowCount;
                        /* System.out.println(lineCount + "," + momentaryRowCount); // to debug */
                        emptyDataCount++;
                        missingDataIndex++;

                    }
                }

                // counting the rows and saving them in the rowControlNumber to see if there is
                // any missing elements in our last column
                if (momentaryRowCount >= rowControlNumber) {
                    rowControlNumber = momentaryRowCount;
                    momentaryRowCount = 0;

                    // if the control number is smaller than the momentary row number, then there is
                    // a missing element in the last column of our dataset
                } else if (momentaryRowCount < rowControlNumber) {

                    // finding the missing element number in our last column
                    // saving the missing border spots' index
                    missingDataPointer[missingDataIndex] = lineCount + "," + (momentaryRowCount + 1);
                    borderDataCount++;
                    missingDataIndex++;
                    momentaryRowCount = 0;
                } else {
                    momentaryRowCount = 0;
                }

                // adding read elements to our arrayList
                dataHolder.add(Arrays.asList(values));
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

        // using for each to iterate amongs all elements of our array.
        for (List<String> list : array) {

            System.out.println(list);

        }
    }

    // to clean up our array from the unwanted results
    public static String[] cleanupMissingDataPointer(String[] array) {

        // iterating inside our array
        int index = 0;

        // using outer function to break all loops when needed
        outer: for (; index < array.length; index++) {

            // creating an index finder to find non zero values
            int notEmptyIndex = index;

            // finding a null value to change
            if (array[index].equals("null")) {

                // finding the next non null value
                while (array[notEmptyIndex].equals("null")) {

                    // to prevent an array outside of bounds error
                    if (notEmptyIndex == array.length - 1) {

                        // breaking all loops because notEmptyIndex is at max value; no need to continue
                        // searching
                        break outer;
                    }
                    notEmptyIndex++;

                }

                // changing the zero value with the non zero one
                array[index] = array[notEmptyIndex];

                // deleting the changed value
                array[notEmptyIndex] = "null";
            }
        }

        // since we know the last index of our number, we can create a new array to
        // cleanup null values
        String[] cleanedArray = new String[index];
        // putting the non null elements into our new cleanedarray
        for (int i = 0; i < cleanedArray.length; i++) {
            cleanedArray[i] = array[i];
        }

        return cleanedArray;
    }

    // to initialize our array
    public static String[] initArray(String[] array) {

        // iterating through arrays elements
        for (int i = 0; i < array.length; i++) {

            // initializing the values with null
            array[i] = "null";
        }
        return array;
    }

    // to print our cleaned array into a txt file
    public static void printMissingDataToTXT(String[] array) {
        // using try catch to create the output file if not exist
        try {
            FileWriter fw = new FileWriter(new File("output.txt"));
            fw.write("The dataset has a total of " + array.length + " missing values.\n");
            for (String string : array) {
                String temp = "Line " + string + ". row.\n";
                fw.write(temp);
            }
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    // to write our taked data to a new CSV file
    public static void writeDataToCSV(String destination) {

    }
}