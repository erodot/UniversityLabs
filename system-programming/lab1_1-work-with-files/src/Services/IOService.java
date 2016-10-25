package Services;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/* Singleton Service for reading text files */
public class IOService {
    private static IOService ourInstance = new IOService();

    public static IOService getInstance() {
        return ourInstance;
    }

    private IOService() {
    }

    public static ArrayList<ArrayList<String>> readFile(String fileName) throws IOException {
        Charset encoding = Charset.defaultCharset();
        File file = new File(fileName);

        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        arrayList.add(new ArrayList<>());

        try(InputStream in = new FileInputStream(file)){
            Reader reader = new InputStreamReader(in, encoding);
            Reader buffer = new BufferedReader(reader);

            StringBuilder lastWord = new StringBuilder("");

            int r;
            while((r = buffer.read()) != -1){
                Character ch = (char) r;

                if((int)ch == 32){ //end of word
                    arrayList.get(arrayList.size()-1) //last row
                            .add(lastWord.toString());
                    lastWord = new StringBuilder(""); //clear buffer
                    continue;
                }

                if((int)ch == 10){ //end of row
                    arrayList.get(arrayList.size()-1) //last row
                            .add(lastWord.toString());
                    lastWord = new StringBuilder(""); //clear buffer
                    arrayList.add(new ArrayList<>());
                    continue;
                }

                if(Character.isLetter(ch)){
                    lastWord.append(ch);
                }

            }

            arrayList.get(arrayList.size()-1) //last row
                    .add(lastWord.toString());
        }

        return arrayList;
    }
}
