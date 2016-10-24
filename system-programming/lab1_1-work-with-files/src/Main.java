import Services.FinderService;
import Services.IOService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class Main {
    public static void main(String [] args) {

        // The name of the file to open
        String fileName = Config.getFileNameWithPath();

        try {
            ArrayList<String> longestConsonantChain;

            //Reading tests from file
            ArrayList<ArrayList<String>> rows = IOService.readFile(fileName);

            for(ArrayList<String> words : rows){
                longestConsonantChain = FinderService.findLongestConsonantChain(words);
                for(String word: longestConsonantChain){
                    System.out.print(word + ' ');
                }
                System.out.println();
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
