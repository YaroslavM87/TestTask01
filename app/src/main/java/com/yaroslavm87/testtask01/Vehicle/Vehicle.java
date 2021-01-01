package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedCurrentSpeed;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedDistanceTravelled;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedFinishTime;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedPunctureProbability;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedState;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;

public abstract class Vehicle implements Observable {

    // TODO: add Command for setting each parameter

    final protected VehicleType vehicleType;
    protected double vehicleMaxSpeed;
    protected double vehicleCurrentSpeed;
    protected double vehiclePunctureProbability;
    protected double vehicleDistanceTravelledInMeters;
    protected long vehicleFinishTime;
    protected VehicleState vehicleState;
    protected RaceManager raceManager;
    protected Publisher publisher;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public abstract void setMaxSpeed(double vehicleMaxSpeed);

    public void setCurrentSpeed(double vehicleCurrentSpeed) {
        if(vehicleCurrentSpeed >= 0 & vehicleCurrentSpeed <= this.vehicleMaxSpeed) {
            this.vehicleCurrentSpeed = vehicleCurrentSpeed;
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedCurrentSpeed()
            );
        }
    }

    public void setPunctureProbability(double vehiclePunctureProbability)  {
        if(vehiclePunctureProbability > 0 & vehiclePunctureProbability < 1) {
            this.vehiclePunctureProbability = vehiclePunctureProbability;
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedPunctureProbability()
            );
        }
    }

    public void setDistanceTravelledInMeters(double vehicleDistanceTravelledInMeters) {
        if(vehicleDistanceTravelledInMeters > 0) {
            this.vehicleDistanceTravelledInMeters = vehicleDistanceTravelledInMeters;
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedDistanceTravelled()
            );
        }
    }

    public void setFinishTime(long finishTime) {
        if(finishTime > 0L) {
            this.vehicleFinishTime = finishTime;
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedFinishTime()
            );
        }
    }

    void setState(VehicleState vehicleState)  {
        if(vehicleState != null) {
            this.vehicleState = vehicleState;
            this.vehicleState.performTaskDefinedByState();
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedState()
            );
        }
    }

    public void changeState(Event event) {
        new VehicleStateChanger().setNextVehicleState(this, event);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getMaxSpeed() {
        return this.vehicleMaxSpeed;
    }

    public double getCurrentSpeed() {
        return this.vehicleCurrentSpeed;
    }

    public double getPunctureProbability() {
        return this.vehiclePunctureProbability;
    }

    public double getDistanceTravelledInMeters() {
        return this.vehicleDistanceTravelledInMeters;
    }

    public long getFinishTime() {
        return this.vehicleFinishTime;
    }

    public VehicleState getState() {
        return this.vehicleState;
    }

    public RaceManager getRaceManager() {
        return raceManager;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setRaceManager(RaceManager raceManager) {
        this.raceManager = raceManager;
    }

    @Override
    public String toString() {
        return vehicleType.toString();
    }
}