package com.yaroslavm87.testtask01.RaceManager;

public class StatePreRaceForRaceManager extends State {

    public StatePreRaceForRaceManager(RaceManager raceManager) {
        super(raceManager);
    }

    @Override
    void setType() {
        super.stateType = StateType.PRE_RACE;
    }

    @Override
    public void performTaskDefinedByState() {
        super.raceManager.setTrackLength(100);
    }
}