package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleCurrentSpeedModelCommand extends ModelCommand {

    private Vehicle vehicle;
    private double vehicleCurrentSpeed;

    public SetVehicleCurrentSpeedModelCommand(Vehicle vehicle, double vehicleCurrentSpeed) {
        super();
        this.vehicle = vehicle;
        this.vehicleCurrentSpeed = vehicleCurrentSpeed;
    }

    @Override
    public void execute() {
        if(vehicleCurrentSpeed >= 0) {
            vehicle.setCurrentSpeed(vehicleCurrentSpeed);
            super.markAsExecuted();
        }
    }
}