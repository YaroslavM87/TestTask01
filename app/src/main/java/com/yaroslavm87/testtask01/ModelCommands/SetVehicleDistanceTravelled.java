package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleDistanceTravelled extends ModelCommand {

    private Vehicle vehicle;
    private double vehicleDistanceTravelledInMeters;

    public SetVehicleDistanceTravelled(Vehicle vehicle, double vehicleDistanceTravelledInMeters) {
        super();
        this.vehicle = vehicle;
        this.vehicleDistanceTravelledInMeters = vehicleDistanceTravelledInMeters;
    }

    @Override
    public void execute() {
        this.vehicle.setDistanceTravelledInMeters(this.vehicleDistanceTravelledInMeters);
        super.markAsExecuted();
    }
}