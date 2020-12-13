package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleSpeed extends ModelCommand {

    private Vehicle vehicle;
    private double vehicleSpeed;

    public SetVehicleSpeed(Vehicle vehicle, double vehicleSpeed) {
        super();
        this.vehicle = vehicle;
        this.vehicleSpeed = vehicleSpeed;
    }

    @Override
    public void execute() {
        if(vehicleSpeed > 0) {
            vehicle.setSpeed(vehicleSpeed);
            super.markAsExecuted();
        }
    }
}