package Services;

import java.util.ArrayList;

// TODO: write tests for FinderService

public class FinderService {
    private static FinderService ourInstance = new FinderService();

    public static FinderService getInstance() {
        return ourInstance;
    }

    private FinderService() {
    }

    public static ArrayList<String> findLongestConsonantChain(final ArrayList<String> words){
        // Getting Iterable array
        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);

        int[] longestChain = new int[words.size()];

        int maxLength = 0;

        for (int i = 0; i < words.size(); i++) {
            longestChain[i] = CounterService.getLongestChain(wordsArray[i]);
            if(longestChain[i]>maxLength)
                maxLength = longestChain[i];
        }

        // Now maxLength value is our longest consonant chain length

        ArrayList<String> maxChainArray = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            if(longestChain[0]==maxLength)
                maxChainArray.add(wordsArray[i]);
        }

        return maxChainArray;
    }
}
