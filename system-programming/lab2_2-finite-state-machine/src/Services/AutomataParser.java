package Services;

import Entities.Automaton;
import Entities.State;
import Entities.Transition;

import java.io.IOException;
import java.util.*;

public class AutomataParser {
    private static AutomataParser ourInstance = new AutomataParser();

    public static AutomataParser getInstance() {
        return ourInstance;
    }

    private AutomataParser() {
    }

    public static Automaton parseFile(String path) throws IOException {

        //Reading tests from file
        ArrayList<String> fileRows = IOService.readFile(path);

        Automaton automaton = new Automaton();

        // set automaton alphabet size
        automaton.alphabetSize = Integer.parseInt(fileRows.get(0));

        // set automaton states
        int stateSize = Integer.parseInt(fileRows.get(1));
        for(int i=0; i<stateSize; i++){
            automaton.states.add(new State(i));
        }

        //set initial state
        automaton.initialState = automaton.getStateByNumber(Integer.parseInt(fileRows.get(2)));

        //set final states
        Arrays
                .stream(fileRows.get(3).split(" "))
                .map(Integer::parseInt)
                .forEach(num -> automaton.finalStates.add(num));


        //extract all rows with transitions
        List<String> transitionsString = fileRows.subList(4, fileRows.size());

        //set transitions
        transitionsString.forEach(transitionString -> {

            String[] elems = transitionString.split(" ");

            int currentStateNumber = Integer.parseInt(elems[0]);
            Character input = elems[1].charAt(0);
            int nextStateNumber = Integer.parseInt(elems[2]);

            State currentState = automaton.getStateByNumber(currentStateNumber);
            State nextState = automaton.getStateByNumber(nextStateNumber);

            currentState.transitions.add(new Transition(input, nextState));
        });


        return automaton;
    }
}
