package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassRaceManagerValueRaceTimerToSubscriberModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.PassRaceManagerValueTrackLengthToSubscriberModelCommand;
import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.RaceEnded;
import com.yaroslavm87.testtask01.Notifications.Events.RaceManagerValueChangedRaceTimer;
import com.yaroslavm87.testtask01.Notifications.Events.RaceManagerValueChangedTrackLength;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;
import java.util.ArrayList;
import java.util.Arrays;


public class RaceManager implements Observable {

    public class RaceTimer implements Observable {

        private double timer;
        private Publisher timerPublisher;

        private RaceTimer(Publisher timerPublisher) {
            setTimerPublisher(timerPublisher);
            this.timer = 0;
        }

        @Override
        public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
            return new PassRaceManagerValueRaceTimerToSubscriberModelCommand(
                    this,
                    subscriber
            );
        }

        @Override
        public void setTimerPublisher(Publisher timerPublisher) {
            this.timerPublisher = timerPublisher;
        }

        private void count(double additionValueToTimer) {
            this.timer = this.timer + additionValueToTimer;
            timerPublisher.notifyEventHappened(this, new RaceManagerValueChangedRaceTimer());
        }

        public double getRaceTimerValue() {
            return timer;
        }
    }

    private State raceManagerState;
    private ArrayList<VehicleType> listOfVehicleTypes;
    private VehicleBuffer vehicleBuffer;
    private VehicleStartList vehicleStartList;
    private double raceTrackLength;
    private Publisher publisher;
    private RaceTimer raceTimer;
    private byte amountOfCarsFinished;

    public RaceManager() {
        this.raceManagerState = new StatePreRaceForRaceManager(this);
        this.listOfVehicleTypes = new ArrayList<>(
                Arrays.asList(VehicleType.values())
        );
        this.listOfVehicleTypes.trimToSize();
        this.vehicleBuffer = new VehicleBuffer();
        this.vehicleStartList = new VehicleStartList();
        this.raceTimer = new RaceTimer(this.publisher);
        this.amountOfCarsFinished = 0;
    }

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new PassRaceManagerValueTrackLengthToSubscriberModelCommand(
                this, subscriber
                );
    }

    @Override
    public void setTimerPublisher(Publisher timerPublisher) {
        this.publisher = timerPublisher;
    }

    public void changeState(Event event) {
        new StateChangerForRaceManager().setNextState(this, event);
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
        if(this.raceManagerState.getType() == StateType.PRE_RACE) {
            this.raceTrackLength = trackLength;
            this.publisher.notifyEventHappened(
                    this, new RaceManagerValueChangedTrackLength()
            );
        }
    }

    public ArrayList<VehicleType> getListOfVehicleTypes() {
        return listOfVehicleTypes;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void raceTimerCount(double additionValueToTimer) {
        this.raceTimer.count(additionValueToTimer);
    }

    public void countVehicleAsFinished() {
        this.amountOfCarsFinished = this.amountOfCarsFinished++;
    }
    public void performActionIfAllVehiclesHaveFinished() {
        if(this.amountOfCarsFinished == this.vehicleStartList.getList().size()) {
            changeState(new RaceEnded());
        }
    }
}