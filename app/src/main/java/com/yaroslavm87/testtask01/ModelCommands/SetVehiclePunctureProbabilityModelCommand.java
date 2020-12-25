package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehiclePunctureProbabilityModelCommand extends ModelCommand {

    private Vehicle vehicle;
    private double punctureProbability;

    public SetVehiclePunctureProbabilityModelCommand(Vehicle vehicle, double punctureProbability) {
        super();
        this.vehicle = vehicle;
        this.punctureProbability = punctureProbability;
    }

    @Override
    public void execute() {
        if(punctureProbability >= 0.5 & punctureProbability < 1.0) {
            vehicle.setPunctureProbability(punctureProbability);
            super.markAsExecuted();
        }
    }
}