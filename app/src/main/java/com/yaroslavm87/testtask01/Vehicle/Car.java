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
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return getAppropriateCommand(event, subscriber);
    }

    @Override
    public void setPublisher(Publisher timerPublisher) {
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

            case VEHICLE_VALUE_CHANGED_FINISH_TIME:
                return new PassVehicleValueFinishTimeToSubscriber(
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