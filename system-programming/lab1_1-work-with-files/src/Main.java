import Services.FinderService;
import Services.IOService;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Main {

    public static void main(String [] args) {
        Main.doLab();
//        Main.createFile();
    }

    public static void doLab() {

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

    public static void createFile() {

        // The name of the file to open
        String fileName = Config.getFileNameWithPath();

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(fileName, true));
            for(int i=0; i<953211187; i++)
            bw.write("some te ");

            bw.write("lognphrase");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {                       // always close the file
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                // just ignore it
            }
        } // end try/catch/finally

    }
}
