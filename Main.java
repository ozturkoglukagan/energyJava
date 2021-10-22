
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String COMMA_DELIMITER = null;
    public static String fileDestination = "empty";
    static List<List<String>> records = new ArrayList<>();
    public static String line = " ";

    public static void main(String[] args) {
        //taking the file destination from user
        fileDestination = args[0];
        //checking if there has been a problem about taking the fileDestination
        if (fileDestination.equals("empty")) {

            System.out.println("File Destination unknown please try again.");

        }
    }

     // to read our file and parsing its values to an arraylist
     public static void readCSVFile(String destination) throws FileNotFoundException {
        // using try catch to prevent the program from crashing
        try {
            // buffered reader reads the input from our file
            BufferedReader br = new BufferedReader(new FileReader(destination));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("an error has ben occured please try again.");

        }

    }

    public static void printArrayListToConsole(List<List<String>> array) {
        for (List<String> list : array) {
            for (List<String> list2 : array) {
                
            }
        }
   }
}