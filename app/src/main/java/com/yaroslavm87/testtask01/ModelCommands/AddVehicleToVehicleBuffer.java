package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.Vehicle.*;

public class AddVehicleToVehicleBuffer extends ModelCommand {

    private Vehicle vehicleToAdd;
    private VehicleBuffer vehicleBuffer;

    public AddVehicleToVehicleBuffer(Vehicle vehicle, VehicleBuffer vehicleBuffer) {
        super();
        this.vehicleToAdd = vehicle;
        this.vehicleBuffer = vehicleBuffer;
    }

    @Override
    public void execute() {
        vehicleBuffer.addVehicleToBuffer(vehicleToAdd);
        super.markAsExecuted();
    }
}