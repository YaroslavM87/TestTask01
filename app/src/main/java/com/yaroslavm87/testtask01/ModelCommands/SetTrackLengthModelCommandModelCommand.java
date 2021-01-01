package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class SetTrackLengthModelCommandModelCommand extends ModelCommand {

    private RaceManager raceManager;
    private double raceTrackLengthInKm;

    public SetTrackLengthModelCommandModelCommand(RaceManager raceManager, double raceTrackLength) {
        super();
        this.raceManager = raceManager;
        this.raceTrackLengthInKm = raceTrackLength;
    }

    @Override
    public void execute() {
        if(this.raceTrackLengthInKm + this.raceManager.getTrackLength() >= 100) {
            double result = this.raceTrackLengthInKm + this.raceManager.getTrackLength();
            this.raceManager.setTrackLength(result);
            super.markAsExecuted();
        }
    }
}