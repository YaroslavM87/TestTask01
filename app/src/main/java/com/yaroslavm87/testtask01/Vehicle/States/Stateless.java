package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class Stateless extends VehicleState {

    public Stateless(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    void setType() {
        super.stateType = VehicleStateType.STATELESS;
    }

    // TODO: show warning message
    @Override
    public void performTaskDefinedByState() {}

}