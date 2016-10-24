package Services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: write tests for IOService

/* Singleton Service for reading text files */
public class IOService {
    private static IOService ourInstance = new IOService();

    public static IOService getInstance() {
        return ourInstance;
    }

    private IOService() {
    }

    public static ArrayList<ArrayList<String>> readFile(String fileName) throws IOException {
        // This will reference one line at a time
        String line;

        ArrayList<String> arrayList = new ArrayList<>();

        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
        }
        catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            throw new IOException("Error reading file '" + fileName + "'");
        }

        ArrayList<ArrayList<String>> arrayListArray = new ArrayList<>();

        for(String row: arrayList){
            //Splitting row into words
            String[] extractedArray = row.split("[^a-zA-Z]{1,}");

            ArrayList<String> extractedArrayList = new ArrayList<>(Arrays.asList(extractedArray));

            extractedArrayList.removeIf(word -> word.length() == 0);

            arrayListArray.add(extractedArrayList);
        }

        return arrayListArray;
    }
}
