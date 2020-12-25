package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleRaceManagerModelCommand extends ModelCommand  {

    private Vehicle vehicle;
    private RaceManager raceManager;

    public SetVehicleRaceManagerModelCommand(Vehicle vehicle, RaceManager raceManager) {
        super();
        this.vehicle = vehicle;
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        this.vehicle.setRaceManager(this.raceManager);
        super.markAsExecuted();
    }
}