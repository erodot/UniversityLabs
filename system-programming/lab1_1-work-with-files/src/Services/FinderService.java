package Services;

import java.util.ArrayList;

/* Singleton Service for finding longest cons chain */
public class FinderService {
    private static FinderService ourInstance = new FinderService();

    public static FinderService getInstance() {
        return ourInstance;
    }

    private FinderService() {
    }

    public static ArrayList<String> findLongestConsonantChain(final ArrayList<String> words){
        for(String word: words){
            if(!word.matches("[a-zA-z]")) // Not a word
                throw new IllegalArgumentException("Array of words contains not only letters.");
        }

        // Getting Iterable array
        String[] wordsArray = new String[words.size()];
        words.toArray(wordsArray);

        int[] longestChain = new int[words.size()];

        int maxLength = 0;

        // Finding max cons chain length
        for (int i = 0; i < words.size(); i++) {
            longestChain[i] = CounterService.getLongestChain(wordsArray[i]);
            if(longestChain[i]>maxLength)
                maxLength = longestChain[i];
        }

        // Now maxLength value is our longest consonant chain length

        ArrayList<String> maxChainArray = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            if(longestChain[i]==maxLength && !maxChainArray.contains(wordsArray[i])) // Add only unique words
                maxChainArray.add(wordsArray[i]);
        }

        return maxChainArray;
    }
}
