package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;

public abstract class Vehicle implements Observable {

//    public class RaceTrackLength implements Subscriber {
//
//        private double raceTrackLengthInMeters;
//
//        public RaceTrackLength() {}
//
//        @Override
//        public void receiveUpdate(Object updatedValue) {
//            if(updatedValue instanceof Double) {
//                this.raceTrackLengthInMeters = (Double) updatedValue;
//            }
//        }
//
//        public double getRaceTrackLengthInMeters() {
//            return raceTrackLengthInMeters;
//        }
//    }

    // TODO: add Command for setting each parameter

    final protected VehicleType vehicleType;
    protected double vehicleMaxSpeed;
    protected double vehicleCurrentSpeed;
    protected double vehiclePunctureProbability;
    protected double vehicleDistanceTravelledInMeters;
    protected VehicleState vehicleState;
    protected RaceManager raceManager;
    protected Publisher publisher;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public abstract void setMaxSpeed(double vehicleMaxSpeed);

    public abstract void setCurrentSpeed(double vehicleCurrentSpeed);

    public abstract void setDistanceTravelledInMeters(double vehicleDistanceTravelledInMeters);

    public abstract void setPunctureProbability(double vehiclePunctureProbability);

    abstract void setState(VehicleState vehicleState);

    public abstract void changeState(Event event);

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getMaxSpeed() {
        return this.vehicleMaxSpeed;
    }

    public double getCurrentSpeed() {
        return this.vehicleCurrentSpeed;
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
        return vehicleType.toString();
    }
}