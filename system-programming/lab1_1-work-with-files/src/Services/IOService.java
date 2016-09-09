package Services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* Singleton Service for reading text files */
public class IOService {
    private static final IOService ourInstance = new IOService();

    public static IOService getInstance() {
        return ourInstance;
    }

    private IOService() {
    }

    public static ArrayList<String> readFile(String fileName){
        // This will reference one line at a time
        String line;

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        return arrayList;
    }
}
