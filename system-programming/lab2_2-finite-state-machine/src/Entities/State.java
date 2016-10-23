package Entities;

import java.util.ArrayList;

public class State {
    public State(int _number){
        this.number = _number;
        this.transitions = new ArrayList<>();
    }

    public int number;
    public ArrayList<Transition> transitions;
}
