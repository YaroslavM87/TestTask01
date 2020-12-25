package com.yaroslavm87.testtask01.RaceManager;

import java.util.Timer;
import java.util.TimerTask;

public class StateRaceForRaceManager extends State {

    private Timer raceTimer;

    public StateRaceForRaceManager(RaceManager raceManager) {
        super(raceManager);
    }

    @Override
    void setType() {
        super.stateType = StateType.RACE;
    }

    @Override
    public void performTaskDefinedByState() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                raceManager.raceTimerCount(100);
                raceManager.performActionIfAllVehiclesHaveFinished();
            }
        };

        raceTimer = new Timer();
        raceTimer.scheduleAtFixedRate(timerTask, 100, 100);
    }
}
