package Services;

import Entities.Automaton;
import Entities.FinalFlag;
import Entities.State;
import Entities.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutomataChecker {

    public AutomataChecker(Automaton _automaton){

        this.automaton = _automaton;

        this.alphabet = this.getAlphabetWithLength(_automaton.alphabetSize);

        this.statePassed = new ArrayList<>();
    }

    private Automaton automaton;
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

    private boolean checkStateToBe(State state , FinalFlag flag){
        // check if we already passed this state
        if(this.statePassed.contains(state))
            return true;

        FinalFlag oppositeFlag;
        if(flag==FinalFlag.Final)
            oppositeFlag = FinalFlag.NotFinal;
        else
            oppositeFlag = FinalFlag.Final;

        long badStatesCount = state.transitions //transition states that have the same final status as current state
                .stream()
                .map(tr -> tr.nextState)
                .distinct()
                .filter(st -> st.finalFlag==flag)
                .count();
        if(badStatesCount>0)
            return false;

        List<Transition> transitions;
        for (Character ch : this.alphabet) {
            transitions = state.transitions
                    .stream()
                    .filter(tr -> tr.input==ch) //transitions which input is current character
                    .collect(Collectors.toList());

            if(transitions.size()!=1)
                return false; //automata is not not deterministic or not working with some symbols
        }

        this.statePassed.add(state);

        //run function with all transition states
        for (Transition tr : state.transitions) {
            if(!this.checkStateToBe(tr.nextState, oppositeFlag))
                return false;
        }

        return true;
    }

    public boolean isValid(){

        List<Transition> transitions;
        for (Character ch : this.alphabet) {
            transitions = automaton.initialState.transitions
                    .stream()
                    .filter(tr -> tr.input==ch) //transitions which input is current character
                    .collect(Collectors.toList());

            if(transitions.size()!=1)
                return false; //automata is not not deterministic or not working with some symbols
        }

        long badStatesCount = automaton.initialState.transitions //transition states that have the same final status as current state
                .stream()
                .map(tr -> tr.nextState)
                .distinct()
                .filter(st -> st.finalFlag==FinalFlag.Final)
                .count();
        if(badStatesCount>0)
            return false;

        //run function with all transition states
        for (Transition tr : automaton.initialState.transitions) {
            if(!this.checkStateToBe(tr.nextState, FinalFlag.NotFinal))
                return false;
        }

        return true;
    }
}
