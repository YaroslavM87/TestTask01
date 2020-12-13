package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class StartRace extends ControllerCommand {

    private RaceManager raceManager;

    public StartRace(RaceManager raceManager) {
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        new com.yaroslavm87.testtask01.ModelCommands.StartRace(this.raceManager).execute();
    }
}