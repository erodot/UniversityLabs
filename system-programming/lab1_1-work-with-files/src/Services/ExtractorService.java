package Services;

import java.util.ArrayList;
import java.util.Arrays;

/* Singleton Service for extracting data from files */
public class ExtractorService {
    private static ExtractorService ourInstance = new ExtractorService();

    public static ExtractorService getInstance() {
        return ourInstance;
    }

    private ExtractorService() {
    }

    public static ArrayList<String> extractWords(String row){

        //Splitting row into words
        String[] extractedRow = row.split("[^a-zA-Z]");

        ArrayList<String> alExtractedRow = new ArrayList<>(Arrays.asList(extractedRow));

        // Deleting all empty strings from the array
        alExtractedRow.removeIf(p -> p.length()==0);

        return alExtractedRow;
    }
}
