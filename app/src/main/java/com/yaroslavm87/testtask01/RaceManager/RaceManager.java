package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassRaceManagerValueTrackLengthToSubscriber;
import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.RaceManagerValueChangedTrackLength;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Arrays;

public class RaceManager implements Observable {

    private State raceManagerState;
    private ArrayList<VehicleType> listOfVehicleTypes;
    private VehicleBuffer vehicleBuffer;
    private VehicleStartList vehicleStartList;
    private double raceTrackLength;
    private Publisher publisher;

    {
        raceTrackLength = 0;
    }

    public RaceManager() {
        this.raceManagerState = new StatePreRace(this);
        this.vehicleBuffer = new VehicleBuffer();
        this.vehicleStartList = new VehicleStartList();
        this.listOfVehicleTypes = new ArrayList<>();
        this.listOfVehicleTypes.addAll(Arrays.asList(VehicleType.values()));
        this.listOfVehicleTypes.trimToSize();
    }

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new PassRaceManagerValueTrackLengthToSubscriber(
                this, subscriber
                );
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void changeState(Event event) {
        new StateChanger().setNextState(this, event);
    }

    void setState(State raceManagerState) {
        if(raceManagerState != null) {
            this.raceManagerState = raceManagerState;
        }
    }

    public State getState() {
        return  this.raceManagerState;
    }

    public VehicleBuffer getVehicleBuffer() {
        return vehicleBuffer;
    }

    public VehicleStartList getVehicleStartList() {
        return vehicleStartList;
    }

    public double getTrackLength() {
        return raceTrackLength;
    }

    public void setTrackLength(double trackLength) {
        this.raceTrackLength = trackLength;
        this.publisher.notifyEventHappened(
                this, new RaceManagerValueChangedTrackLength()
        );
    }

    public ArrayList<VehicleType> getListOfVehicleTypes() {
        return listOfVehicleTypes;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}