package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;

public abstract class Vehicle implements Observable {

    // TODO: add Command for setting each parameter

    final VehicleType vehicleType;
    double vehicleSpeed;
    double vehiclePunctureProbability;
    double vehicleDistanceTravelledInMeters;
    VehicleState vehicleState;
    RaceManager raceManager;
    Publisher publisher;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public abstract void setSpeed(double vehicleSpeed);

    public abstract void setDistanceTravelledInMeters(double vehicleDistanceTravelledInMeters);

    public abstract void setPunctureProbability(double vehiclePunctureProbability);

    abstract void setState(VehicleState vehicleState);

    public abstract void changeState(Event event);

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getCurrentSpeed() {
        return this.vehicleSpeed;
    }

    public double getDistanceTravelledInMeters() {
        return this.vehicleDistanceTravelledInMeters;
    }

    public double getPunctureProbability() {
        return this.vehiclePunctureProbability;
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
        return "Vehicle Type=" + vehicleType +
                '}';
    }
}