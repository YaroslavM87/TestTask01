package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.Vehicle.*;

public class AddVehicleToVehicleBuffer extends ModelCommand {

    private Vehicle vehicleToAdd;
    private RaceManager raceManager;

    public AddVehicleToVehicleBuffer(Vehicle vehicle, RaceManager raceManager) {
        super();
        this.vehicleToAdd = vehicle;
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        raceManager.addVehicleToBuffer(vehicleToAdd);
        super.markAsExecuted();
    }
}