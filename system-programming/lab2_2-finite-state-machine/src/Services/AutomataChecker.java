package Services;

import Entities.Automaton;
import Entities.FinalFlag;
import Entities.State;
import Entities.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutomataChecker {

    private Automaton automaton;
    private ArrayList<ArrayList<State>> automaton_memory;
    private ArrayList<State> statePassed;
    private Character[] alphabet;

    private Character[] getAlphabetWithLength(int length){
        if(length <= 0)
            return new Character[0];

        Character[] alphabet = new Character[length];

        for(int i=0; i<length; i++) {
            alphabet[i] = (char) (Character.valueOf('a') + i);
        }

        return alphabet;
    }

    public AutomataChecker(Automaton _automaton){

        this.automaton = _automaton;

        this.automaton_memory = new ArrayList<>();

        this.alphabet = this.getAlphabetWithLength(_automaton.alphabetSize);

        this.statePassed = new ArrayList<>();
    }

    private ArrayList<State> GoNext(ArrayList<State> automaton_state){
        ArrayList<State> new_automaton_state = new ArrayList<>();

        for (State st :
                automaton_state) {
            new_automaton_state.addAll(st.transitions.stream().map(tr -> tr.nextState).collect(Collectors.toList()));
        }

        return new_automaton_state;
    }

    private boolean IsIteration(ArrayList<State> automaton_state){

        upper:
        for (ArrayList<State> previous_automaton_state :
                automaton_memory) {

            //check if automaton_state is subset of previous_automaton_state
            for(int counter = 0; counter < automaton_state.size(); counter++) {
                if(!previous_automaton_state.contains(automaton_state.get(counter)))
                    continue upper;
            }

            //check if previous_automaton_state is subset of automaton_state
            for(int counter = 0; counter < previous_automaton_state.size(); counter++) {
                if(!automaton_state.contains(previous_automaton_state.get(counter)))
                    continue upper;
            }

            return true;
        }

        return false;
    }

    public boolean isValid(){

        // set initial parameters
        int depth = 0;
        ArrayList<State> automaton_state = new ArrayList<>();
        automaton_state.add(automaton.initialState);

        // first iteration
        automaton_state = GoNext(automaton_state);
        depth ++;

        while(true){
            if(depth % 2 == 0) { // even word length
                boolean has_final = false;

                if(IsIteration(automaton_state))
                    return true;

                for (State st : automaton_state)
                    if(st.finalFlag == FinalFlag.Final)
                        has_final = true;

                if(has_final)
                    automaton_memory.add(automaton_state);
                else
                    return false; // there is no final state on words with even length

            }
            else { // odd word length
                for (State st : automaton_state)
                    if(st.finalFlag == FinalFlag.Final)
                        return false; // exit if there is final state on odd word length
            }

            automaton_state = GoNext(automaton_state);
            depth ++;
        }

    }
}
