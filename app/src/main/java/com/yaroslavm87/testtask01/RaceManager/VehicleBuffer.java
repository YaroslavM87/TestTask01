package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassEmptyValueToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassVehicleAsValueToSubscriberModelCommand;
import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleBufferNewVehicleAdded;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleBufferVehicleDeleted;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class VehicleBuffer implements Observable {

    private Vehicle vehicleFromBuffer;
    private Publisher publisher;

    public VehicleBuffer() {}

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {

        switch(event.getType()) {
            case VEHICLE_BUFFER_NEW_VEHICLE_ADDED:
                return new PassVehicleAsValueToSubscriberModelCommand(this, subscriber);

            case VEHICLE_BUFFER_VEHICLE_DELETED:
                return new PassEmptyValueToSubscriberModelCommand(subscriber);

            default:
                return null;
        }
    }

    public void addVehicleToBuffer(Vehicle vehicle) {
        this.vehicleFromBuffer = vehicle;
        this.publisher.notifyEventHappened(this, new VehicleBufferNewVehicleAdded());
    }

    public Vehicle getVehicleFromBuffer() {
        return this.vehicleFromBuffer;
    }

    public void deleteVehicleFromBuffer() {
        this.vehicleFromBuffer = null;
        this.publisher.notifyEventHappened(this, new VehicleBufferVehicleDeleted()); //TODO: add new command to clear textViews
    }

    public void setPublisher(Publisher timerPublisher) {
        this.publisher = timerPublisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}