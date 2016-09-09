import Services.ExtractorService;
import Services.FinderService;
import Services.IOService;

import java.util.ArrayList;

class Main {
    public static void main(String [] args) {

        // The name of the file to open
        String fileName = Config.getFileNameWithPath();

        ArrayList<String> words;
        ArrayList<String> longestConsonantChain;

        //Reading tests from file
        ArrayList<String> tests = IOService.readFile(fileName);

        for(String testLine : tests){
            words = ExtractorService.extractWords(testLine);
            longestConsonantChain = FinderService.findLongestConsonantChain(words);
            longestConsonantChain.forEach(System.out::println);
        }
    }
}
