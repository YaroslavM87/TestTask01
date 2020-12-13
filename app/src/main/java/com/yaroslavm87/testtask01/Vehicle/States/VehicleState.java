package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.Vehicle.*;

public abstract class VehicleState {

    VehicleStateType stateType;
    Vehicle vehicle;

    public VehicleState(Vehicle vehicle) {
        this.vehicle = vehicle;
        setType();
    }

    abstract void setType();

    public abstract void performTaskDefinedByState();

    public VehicleStateType getType() {
        return this.stateType;
    }

}