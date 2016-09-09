package Lab;

import java.util.ArrayList;

public class Lab {

    // Constructor
    public Lab(){}

    // Class entry point
    public void work(String fileName){
        ArrayList<String> tests = IOService.readFile(fileName);

        // TODO: implement line processing
        tests.forEach(System.out::println);
    }
}
