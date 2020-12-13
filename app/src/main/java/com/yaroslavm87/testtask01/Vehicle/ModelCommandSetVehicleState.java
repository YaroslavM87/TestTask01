package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class ModelCommandSetVehicleState extends ModelCommand {

    private Vehicle vehicle;
    private VehicleState vehicleState;

    public ModelCommandSetVehicleState(Vehicle vehicle, VehicleState vehicleState) {
        super();
        this.vehicle = vehicle;
        this.vehicleState = vehicleState;
    }

    @Override
    public void execute() {
        this.vehicle.setState(this.vehicleState);
        super.markAsExecuted();
    }
}
