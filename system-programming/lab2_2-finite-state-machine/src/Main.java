import Config.Config;
import Entities.Automaton;
import Services.AutomataParser;

import java.io.IOException;

public class Main {
    public static void main(String [] args) {
        try{
            //build automaton from file
            Automaton automaton = AutomataParser.parseFile(Config.getFileNameWithPath());

        } catch (IOException ex){
            System.out.print(ex.getMessage());
        }
    }
}
