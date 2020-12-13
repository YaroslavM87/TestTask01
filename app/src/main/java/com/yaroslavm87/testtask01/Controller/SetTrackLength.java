package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.SetTrackLengthInKm;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class SetTrackLength extends ControllerCommand {

    private RaceManager raceManager;
    private double trackLengthAdditionalValue;

    public SetTrackLength(RaceManager raceManager, double trackLengthAdditionalValue) {
        this.raceManager = raceManager;
        this.trackLengthAdditionalValue = trackLengthAdditionalValue;
    }

    @Override
    public void execute() {
        new SetTrackLengthInKm(raceManager, trackLengthAdditionalValue).execute();
    }
}