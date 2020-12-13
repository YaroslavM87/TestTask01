package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class Finished extends VehicleState {

    public Finished(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    void setType() {
        super.stateType = VehicleStateType.FINISHED;
    }

    @Override
    public void performTaskDefinedByState() {
    }
}