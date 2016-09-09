package Services;

import java.util.ArrayList;

public class ExtractorService {
    private static ExtractorService ourInstance = new ExtractorService();

    public static ExtractorService getInstance() {
        return ourInstance;
    }

    private ExtractorService() {
    }

    public static ArrayList<String> extractWords(String row){
        // TODO: implement Services.ExtractorService
        return new ArrayList<>();
    }
}
