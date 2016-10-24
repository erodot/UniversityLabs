package Entities;

import java.util.ArrayList;

public class State {
    public State(int _number){
        this.number = _number;
        this.transitions = new ArrayList<>();
        this.finalFlag = FinalFlag.NotFinal;
    }

    public int number;
    public FinalFlag finalFlag;
    public ArrayList<Transition> transitions;
}
