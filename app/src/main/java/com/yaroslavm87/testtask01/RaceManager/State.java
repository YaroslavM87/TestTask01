package com.yaroslavm87.testtask01.RaceManager;

public abstract class State {

    StateType stateType;
    RaceManager raceManager;

    public State(RaceManager raceManager) {
        this.raceManager = raceManager;
        setType();
    }

    abstract void setType();

    public abstract void performTaskDefinedByState();

    public StateType getType() {
        return this.stateType;
    }
}
