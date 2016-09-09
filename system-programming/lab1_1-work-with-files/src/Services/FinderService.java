package Services;

import java.util.ArrayList;

public class FinderService {
    private static FinderService ourInstance = new FinderService();

    public static FinderService getInstance() {
        return ourInstance;
    }

    private FinderService() {
    }

    public static ArrayList<String> findLongestConsonantChain(ArrayList<String> words){
        // TODO: implement and write tests for FinderService
        return words;
    }
}
