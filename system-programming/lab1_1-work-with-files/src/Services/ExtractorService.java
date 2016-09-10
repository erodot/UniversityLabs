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
        String[] extractedArray = row.split("[^a-zA-Z]{1,}");

        ArrayList<String> extractedArrayList = new ArrayList<>(Arrays.asList(extractedArray));

        extractedArrayList.removeIf(word -> word.length() == 0);

        return extractedArrayList;
    }
}
