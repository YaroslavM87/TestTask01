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
import com.yaroslavm87.testtask01.Singletone;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;
import java.util.ArrayList;
import java.util.Arrays;


public class RaceManager implements Observable {

    public static class RaceTimer implements Observable {

        private long timer;
        private Publisher timerPublisher;

        private RaceTimer() {
            this.timer = 0L;
        }

        @Override
        public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
            return new PassRaceManagerValueRaceTimerToSubscriberModelCommand(
                    this,
                    subscriber
            );
        }

        @Override
        public void setPublisher(Publisher timerPublisher) {
            this.timerPublisher = timerPublisher;
        }

        private void count(long additionValueToTimer) {
            this.timer = additionValueToTimer + this.timer;
            timerPublisher.notifyEventHappened(this, new RaceManagerValueChangedRaceTimer());
        }

        public long getRaceTimerValue() {
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
        setState(new StatePreRaceForRaceManager(this));
        this.listOfVehicleTypes = new ArrayList<>(
                Arrays.asList(VehicleType.values())
        );
        this.listOfVehicleTypes.trimToSize();
        this.vehicleBuffer = new VehicleBuffer();
        this.vehicleStartList = new VehicleStartList();
        this.raceTimer = new RaceTimer();
        this.amountOfCarsFinished = 0;

        Singletone.raceManager = this;
    }

    @Override
    public ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber) {
        return new PassRaceManagerValueTrackLengthToSubscriberModelCommand(
                this, subscriber
                );
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        this.raceTimer.setPublisher(publisher);
    }

    public void changeState(Event event) {
        new StateChangerForRaceManager().setNextState(this, event);
    }

    void setState(State raceManagerState) {
        if(raceManagerState != null) {
            this.raceManagerState = raceManagerState;
            this.raceManagerState.performTaskDefinedByState();
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

            if(this.publisher != null) {
                this.publisher.notifyEventHappened(
                        this,
                        new RaceManagerValueChangedTrackLength()
                );
            }
        }
    }

    public ArrayList<VehicleType> getListOfVehicleTypes() {
        return listOfVehicleTypes;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void raceTimerCount(long additionValueToTimer) {
        this.raceTimer.count(additionValueToTimer);
    }

    public RaceTimer getRaceTimer() {
        return raceTimer;
    }

    public void countVehicleAsFinished() {
        this.amountOfCarsFinished++;
    }

    // TODO: fix bug occurring when "changeState(new RaceEnded())" finishes faster then "state.cancelTimer();"
    //  java.lang.ClassCastException: com.yaroslavm87.testtask01.RaceManager.StatePostRace cannot be cast to com.yaroslavm87.testtask01.RaceManager.StateRaceForRaceManager

    public void performActionIfAllVehiclesHaveFinished() {
        if(this.amountOfCarsFinished == this.vehicleStartList.getList().size()) {
            StateRaceForRaceManager state = (StateRaceForRaceManager) this.raceManagerState;
            state.cancelTimer();
            changeState(new RaceEnded());
        }
    }
}