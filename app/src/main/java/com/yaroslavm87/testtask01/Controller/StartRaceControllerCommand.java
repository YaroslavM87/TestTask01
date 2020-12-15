package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.StartRaceModelCommand;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class StartRaceControllerCommand extends ControllerCommand {

    private RaceManager raceManager;

    public StartRaceControllerCommand(RaceManager raceManager) {
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        new StartRaceModelCommand(this.raceManager).execute();
    }
}