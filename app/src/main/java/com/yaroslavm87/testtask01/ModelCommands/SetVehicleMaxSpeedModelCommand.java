package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleMaxSpeedModelCommand extends ModelCommand {

    private Vehicle vehicle;
    private double vehicleMaxSpeed;

    public SetVehicleMaxSpeedModelCommand(Vehicle vehicle, double vehicleMaxSpeed) {
        super();
        this.vehicle = vehicle;
        this.vehicleMaxSpeed = vehicleMaxSpeed;
    }

    @Override
    public void execute() {
        this.vehicle.setMaxSpeed(vehicleMaxSpeed);
        super.markAsExecuted();
    }
}