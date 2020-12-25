package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.ModelCommands.*;
import com.yaroslavm87.testtask01.Notifications.Events.*;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.States.*;

public class Car extends Vehicle {

    VehicleAdditionalValue amountOfPassengers;

    public Car() {
        super(VehicleType.CAR);
    }

    @Override
    public void setMaxSpeed(double vehicleMaxSpeed) {
        if(vehicleMaxSpeed > 40 & vehicleMaxSpeed < 120) {
            super.vehicleMaxSpeed = vehicleMaxSpeed;
            super.raceManager.getVehicleBuffer().getPublisher().notifyEventHappened(
                    this, new VehicleValueChangedMaxSpeed()
            );
        }
    }

    @Override
    public void setCurrentSpeed(double vehicleSpeed) {
        if(vehicleSpeed >= 0 & vehicleSpeed <= super.vehicleMaxSpeed) {
            super.vehicleCurrentSpeed = vehicleSpeed;
            this.publisher.notifyEventHappened(
                    this, new VehicleValueChangedCurrentSpeed()
            );
        }
    }

    @Override
    public void setDistanceTravelledInMeters(double vehicleDistanceTravelledInMeters) {
        if(vehicleDistanceTravelledInMeters > 0) {
            super.vehicleDistanceTravelledInMeters = vehicleDistanceTravelledInMeters;
            super.publisher.notifyEventHappened(
                    this, new VehicleValueChangedDistanceTravelled()
            );
        }
    }

    @Override
    public void setPunctureProbability(double vehiclePunctureProbability) {
        if(vehiclePunctureProbability > 0 & vehiclePunctureProbability < 1) {
            this.vehiclePunctureProbability = vehiclePunctureProbability;
            super.publisher.notifyEventHappened(
                    this, new VehicleValueChangedPunctureProbability()
            );
        }
    }

    @Override
    void setState(VehicleState vehicleState) {
        if(vehicleState != null) {
            this.vehicleState = vehicleState;
            this.vehicleState.performTaskDefinedByState();
            super.publisher.notifyEventHappened(
                    this, new VehicleValueChangedState()
            );
        }
    }

    //TODO: add command for this method
    @Override
    public void changeState(Event event) {
        new VehicleStateChanger().setNextVehicleState(this, event);
    }

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return getAppropriateCommand(event, subscriber);
    }

    @Override
    public void setTimerPublisher(Publisher timerPublisher) {
        super.publisher = timerPublisher;
    }

    public void setAmountOfPassengers(int amountOfPassengers) {
        this.amountOfPassengers = new VehicleAdditionalValue(amountOfPassengers);
    }

    private ModelCommand getAppropriateCommand(Event event, Subscriber subscriber) {

        switch(event.getType()) {

            case VEHICLE_TYPE_CHANGED:
                return new PassVehicleTypeToSubscriberModelCommand(
                        this, subscriber
                );

            case VEHICLE_VALUE_CHANGED_MAX_SPEED:
                return new PassVehicleValueMaxSpeedToSubscriberModelCommand(
                        this, subscriber
                );

            case VEHICLE_VALUE_CHANGED_CURRENT_SPEED:
                return new PassVehicleValueCurrentSpeedToSubscriberModelCommand(
                        this, subscriber
                );

            case VEHICLE_VALUE_CHANGED_PUNCTURE_PROBABILITY:
                return new PassVehicleValuePunctureProbabilityToSubscriberModelCommand(
                        this, subscriber
                );

            case VEHICLE_VALUE_CHANGED_DISTANCE_TRAVELLED:
                return new PassVehicleValueDistanceTravelledToSubscriber(
                        this, subscriber
                );

            case VEHICLE_VALUE_CHANGED_STATE:
                return new PassVehicleValueStateToSubscriberModelCommand(
                        this, subscriber
                );

            default: return null;
        }
    }
}