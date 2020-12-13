package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.Vehicle.*;

public class Initialized extends VehicleState {

    public Initialized(Vehicle vehicle) {
        super(vehicle);
        }

    @Override
    void setType() {
        super.stateType = VehicleStateType.INITIALIZED_AND_READY_TO_START;
    }

    @Override
    public void performTaskDefinedByState() {}

}