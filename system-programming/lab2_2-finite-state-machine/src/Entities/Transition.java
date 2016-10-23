package Entities;

public class Transition {
    public Transition(Character _input, State _nextState){
        this.input = _input;
        this.nextState = _nextState;
    }

    public Character input;
    public State nextState;
}
