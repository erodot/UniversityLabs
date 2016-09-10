package Services;

/* Singleton Service for counting longest cons chain */
public class CounterService {
    private static CounterService ourInstance = new CounterService();

    public static CounterService getInstance() {
        return ourInstance;
    }

    private CounterService() {
    }

    public static int getLongestChain(String word){
        word = word.toLowerCase();
        String vowels = "aeiouy";
        int currChain = 0;
        int maxChain = 0;
        for (int i = 0; i < word.length(); i++) {
            if(vowels.indexOf(word.charAt(i)) == -1)
            {
                currChain++;
                if(currChain > maxChain)
                    maxChain = currChain;
            }
            else
                currChain = 0;
        }
        
        return maxChain;
    }
}
