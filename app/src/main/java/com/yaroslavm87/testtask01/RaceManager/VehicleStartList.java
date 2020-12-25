package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassUpdateFromVehicleListToSubscriberModelCommand;
import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleStartListNewVehicleAdded;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleStartListVehicleDeleted;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.ArrayList;

public class VehicleStartList implements Observable {

    private ArrayList<Vehicle> startList;
    private Publisher publisher;
    private EventType currentEventType;
    private int indexOfVehicleBeingRemoved;


    public VehicleStartList() {
        startList = new ArrayList<>();
    }

    public void addVehicleToStartList(Vehicle vehicle) {
        if(vehicle != null) {
            startList.add(vehicle);
            currentEventType = EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED;
            publisher.notifyEventHappened(this, new VehicleStartListNewVehicleAdded());
        }
    }

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new PassUpdateFromVehicleListToSubscriberModelCommand(this, subscriber);
    }

    @Override
    public void setPublisher(Publisher timerPublisher) {
        this.publisher = timerPublisher;
    }

    public Vehicle getVehicleFromStartList(int index) {
        return startList.get(index);
    }

    public void deleteVehicleFromStartList(Vehicle vehicle) {
        indexOfVehicleBeingRemoved = startList.indexOf(vehicle);
        startList.remove(indexOfVehicleBeingRemoved);
        currentEventType = EventType.VEHICLE_START_LIST_VEHICLE_DELETED;
        publisher.notifyEventHappened(this, new VehicleStartListVehicleDeleted());
    }

    public Vehicle deleteVehicleFromStartList(int index) {
        Vehicle result = startList.remove(index);
        publisher.notifyEventHappened(this, new VehicleStartListNewVehicleAdded());
        return result;
    }

    public int getVehicleStartListLength() {
        return this.startList.size();
    }

    public EventType getCurrentEventType() {
        return currentEventType;
    }

    public int getIndexOfVehicleBeingRemoved() {
        return indexOfVehicleBeingRemoved;
    }

    public ArrayList<Vehicle> getList() {
        return startList;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}