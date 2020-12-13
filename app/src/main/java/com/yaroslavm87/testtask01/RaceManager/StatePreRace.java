package com.yaroslavm87.testtask01.RaceManager;

public class StatePreRace extends State {


    public StatePreRace(RaceManager raceManager) {
        super(raceManager);
    }

    @Override
    void setType() {
        super.stateType = StateType.PRE_RACE;
    }

    @Override
    public void performTaskDefinedByState() {
    }
}
