package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleTypeChanged;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedMaxSpeed;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedPunctureProbability;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedCurrentSpeed;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;

public class NotifySubscribersVehicleValuesChanged extends ModelCommand implements Observable {

    private VehicleBuffer sender;
    private Event event;

    public NotifySubscribersVehicleValuesChanged(VehicleBuffer vehicleBuffer, Event event) {
        super();
        this.sender = vehicleBuffer;
        this.event = event;
    }

    @Override
    public void execute() {
        if(event.getType() == EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED) {
            sender.getPublisher().notifyEventHappened(sender.getVehicleFromBuffer(), new VehicleTypeChanged());
            sender.getPublisher().notifyEventHappened(sender.getVehicleFromBuffer(), new VehicleValueChangedMaxSpeed());
            sender.getPublisher().notifyEventHappened(sender.getVehicleFromBuffer(), new VehicleValueChangedPunctureProbability());
            //sender.getPublisher().notifyEventHappened(sender, new VehicleValueChangedAdditionalValue());
        } else if(event.getType() == EventType.VEHICLE_BUFFER_VEHICLE_DELETED) {
            sender.getPublisher().notifyEventHappened(this, new VehicleTypeChanged());
            sender.getPublisher().notifyEventHappened(this, new VehicleValueChangedMaxSpeed());
            sender.getPublisher().notifyEventHappened(this, new VehicleValueChangedPunctureProbability());
        }
    }

    // TODO: find another way
    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new PassEmptyValueToSubscriber(subscriber);
    }

    @Override
    public void setPublisher(Publisher publisher) {
    }
}