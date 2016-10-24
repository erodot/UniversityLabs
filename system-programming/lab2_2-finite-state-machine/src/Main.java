import Config.Config;
import Entities.Automaton;
import Services.AutomataChecker;
import Services.AutomataParser;

import java.io.IOException;

public class Main {
    public static void main(String [] args) {
        try{
            //build automaton from file
            Automaton automaton = AutomataParser.parseFile(Config.getFileNameWithPath());
            AutomataChecker automataChecker = new AutomataChecker(automaton);

            if(automataChecker.isValid()){
                System.out.println("Automaton is valid");
            }
            else{
                System.out.println("Automaton is invalid");
            }

        } catch (IOException ex){
            System.out.print(ex.getMessage());
        }
    }
}
