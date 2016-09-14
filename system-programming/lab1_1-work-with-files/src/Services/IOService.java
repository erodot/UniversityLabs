package Services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// TODO: write tests for IOService

/* Singleton Service for reading text files */
public class IOService {
    private static IOService ourInstance = new IOService();

    public static IOService getInstance() {
        return ourInstance;
    }

    private IOService() {
    }

    public static ArrayList<String> readFile(String fileName) throws IOException {
        // This will reference one line at a time
        String line;

        ArrayList<String> arrayList = new ArrayList<>();

        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
        }
        catch(FileNotFoundException ex) {
            throw new FileNotFoundException("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            throw new IOException("Error reading file '" + fileName + "'");
        }

        return arrayList;
    }
}
