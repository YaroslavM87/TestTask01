package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class SetTrackLengthInKm extends ModelCommand {

    private RaceManager raceManager;
    private double raceTrackLengthInKm;

    public SetTrackLengthInKm(RaceManager raceManager, double raceTrackLengthInKm) {
        super();
        this.raceManager = raceManager;
        this.raceTrackLengthInKm = raceTrackLengthInKm;
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