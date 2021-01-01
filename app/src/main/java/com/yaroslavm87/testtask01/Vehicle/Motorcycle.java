package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleTypeToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValueCurrentSpeedToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValueDistanceTravelledToSubscriber;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValueFinishTimeToSubscriber;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValueMaxSpeedToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValuePunctureProbabilityToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleValueStateToSubscriberModelCommand;
import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedMaxSpeed;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;

public class Motorcycle extends Vehicle {

    VehicleAdditionalValue hasCradle;

    public Motorcycle() {
        super(VehicleType.MOTORCYCLE);
    }

    @Override
    public void setMaxSpeed(double vehicleMaxSpeed) {
        if(vehicleMaxSpeed > 60 & vehicleMaxSpeed < 160) {
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

    public void setHasCradle(boolean hasCradle) {
        this.hasCradle = new VehicleAdditionalValue(hasCradle);
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
