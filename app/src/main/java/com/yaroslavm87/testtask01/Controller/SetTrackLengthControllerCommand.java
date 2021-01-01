package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.SetTrackLengthModelCommandModelCommand;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class SetTrackLengthControllerCommand extends ControllerCommand {

    private RaceManager raceManager;
    private double trackLengthAdditionalValue;

    public SetTrackLengthControllerCommand(RaceManager raceManager, double trackLengthAdditionalValue) {
        this.raceManager = raceManager;
        this.trackLengthAdditionalValue = trackLengthAdditionalValue;
    }

    @Override
    public void execute() {
        new SetTrackLengthModelCommandModelCommand(raceManager, trackLengthAdditionalValue).execute();
    }
}