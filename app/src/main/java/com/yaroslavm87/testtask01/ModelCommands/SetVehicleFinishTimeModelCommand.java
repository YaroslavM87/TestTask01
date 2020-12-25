package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleFinishTimeModelCommand extends ModelCommand {

    private Vehicle vehicle;
    private long vehicleFinishTime;

    public SetVehicleFinishTimeModelCommand(Vehicle vehicle, long vehicleFinishTime) {
        super();
        this.vehicle = vehicle;
        this.vehicleFinishTime = vehicleFinishTime;
    }

    @Override
    public void execute() {
        this.vehicle.setFinishTime(this.vehicleFinishTime);
        super.markAsExecuted();
    }
}