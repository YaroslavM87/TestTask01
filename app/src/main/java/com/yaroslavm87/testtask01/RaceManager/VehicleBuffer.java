package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.NotifySubscribersVehicleValuesChanged;
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
    private EventType eventType;

    public VehicleBuffer() {}

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new NotifySubscribersVehicleValuesChanged(this, event);
    }

    void addVehicleToBuffer(Vehicle vehicle) {
        this.vehicleFromBuffer = vehicle;
        eventType = EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED;
        publisher.notifyEventHappened(this, new VehicleBufferNewVehicleAdded());
    }

    public Vehicle getVehicleFromBuffer() {
        return this.vehicleFromBuffer;
    }

    public void deleteVehicleFromBuffer() {
        this.vehicleFromBuffer = null;
        this.eventType = EventType.VEHICLE_BUFFER_VEHICLE_DELETED;
        this.publisher.notifyEventHappened(this, new VehicleBufferVehicleDeleted()); //TODO: add new command to clear textViews
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public EventType getEventType() {
        return eventType;
    }
}