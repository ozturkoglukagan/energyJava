import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public static void main(String[] args) throws FileNotFoundException {
        // taking the file destination from user
        fileDestination = args[0];
        // checking if there has been a problem about taking the fileDestination
        if (fileDestination.equals("empty")) {

            System.out.println("File Destination unknown please try again.");

        }
        System.out.println("this works");
        System.out.println("line count;" + lineCount);
        System.out.println("row count;" + rowControlNumber);
        System.out.println("data count;" + insideDataCount);
        System.out.println("emptydata;" + emptyDataCount);
        long startTime = System.currentTimeMillis();
        readCSVFile(fileDestination);
        long endTime = System.currentTimeMillis();
        System.out.println("time elapsed ms:" + (endTime - startTime));
        System.out.println("line count;" + lineCount);
        System.out.println("row count;" + rowControlNumber);
        System.out.println("data count;" + insideDataCount);
        System.out.println("border data count;" + borderDataCount);
        System.out.println("emptydata;" + emptyDataCount);
        printArrayListToConsole(dataHolder);
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
                // taking the linecount to use after to print into a new file
                lineCount++;
                // splitting data with commas 
                String[] values = line.split(",");

                // looking for missing data spots
                for (String string : values) {
                    // counting the rows
                    momentaryRowCount++;
                    // counting the data
                    insideDataCount++;
                    // putting an if statement to check if the data spot is empty
                    if (string.equals("")) {
                        emptyDataCount++;
                    }
                    

                }
                
                // counting the rows and saving them in the rowControlNumber to see if there is any missing elements in our last column
                if (momentaryRowCount>=rowControlNumber) { 
                    rowControlNumber=momentaryRowCount; momentaryRowCount=0; 
                //if the control number is smaller than the momentary row number, then there is a missing element in the last column of our dataset
                }else if(momentaryRowCount<rowControlNumber){
                    //finding the missing element number in our last column
                    borderDataCount++;
                }else{momentaryRowCount=0;}
                 
                // adding read elements to our array
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

    // to write our taked data to a new CSV file
    public static void writeDataToCSV(String destination) {

    }
}