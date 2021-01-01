package com.yaroslavm87.testtask01.RaceManager;

public class StatePostRaceForRaceManager extends State {


    public StatePostRaceForRaceManager(RaceManager raceManager) {
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
