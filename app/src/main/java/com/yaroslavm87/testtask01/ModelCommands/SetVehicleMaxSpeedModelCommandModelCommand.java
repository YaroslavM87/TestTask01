package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleMaxSpeedModelCommandModelCommand extends ModelCommand {

    private Vehicle vehicle;
    private double vehicleMaxSpeed;

    public SetVehicleMaxSpeedModelCommandModelCommand(Vehicle vehicle, double vehicleMaxSpeed) {
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