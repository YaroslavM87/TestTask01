package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetRaceManagerStateModelCommand extends ModelCommand {

    private RaceManager raceManager;
    private State raceManagerState;

    public SetRaceManagerStateModelCommand(RaceManager raceManager, State raceManagerState) {
        super();
        this.raceManager = raceManager;
        this.raceManagerState = raceManagerState;
    }

    @Override
    public void execute() {
        this.raceManager.setState(this.raceManagerState);
        super.markAsExecuted();
    }
}
