package Entities;

import java.util.ArrayList;
import java.util.Optional;

public class Automaton {
    public Automaton(){
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
    }

    public int alphabetSize;
    public ArrayList<State> states;
    public State initialState;
    public ArrayList<Integer> finalStates;

    public State getStateByNumber(int stateNumber){
        Optional<State> stateOptional = this.states
                .stream()
                .filter(st -> st.number == stateNumber)
                .findFirst();

        if(stateOptional.isPresent()) {
            return stateOptional.get();
        }
        else {
            return null;
        }
    }
}
