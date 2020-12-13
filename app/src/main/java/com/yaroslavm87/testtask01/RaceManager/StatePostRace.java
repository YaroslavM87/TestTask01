package com.yaroslavm87.testtask01.RaceManager;

public class StatePostRace extends State {


    public StatePostRace(RaceManager raceManager) {
        super(raceManager);
    }

    @Override
    void setType() {
        super.stateType = StateType.POST_RACE;
    }

    @Override
    public void performTaskDefinedByState() {
    }
}
