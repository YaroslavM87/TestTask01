package com.yaroslavm87.testtask01.RaceManager;

public class StateRace extends State {


    public StateRace(RaceManager raceManager) {
        super(raceManager);
    }

    @Override
    void setType() {
        super.stateType = StateType.RACE;
    }

    @Override
    public void performTaskDefinedByState() {
    }
}
