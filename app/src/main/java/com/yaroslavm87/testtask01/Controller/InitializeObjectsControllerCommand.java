package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class InitializeObjectsControllerCommand extends ControllerCommand {

    private RaceManager raceManager;

    @Override
    public void execute() {
        this.raceManager = new RaceManager();

        Publisher vehicleRaceManagerPublisher = new Publisher();
        new SetPublisherToObservable(raceManager, vehicleRaceManagerPublisher).execute();

        Publisher vehicleBufferPublisher = new Publisher();
        new SetPublisherToObservable(this.raceManager.getVehicleBuffer(), vehicleBufferPublisher).execute();

        Publisher vehicleStartListPublisher = new Publisher();
        new SetPublisherToObservable(this.raceManager.getVehicleStartList(), vehicleStartListPublisher).execute();

        this.raceManager.getState().performTaskDefinedByState();
    }

    public RaceManager getRaceManager() {
        return this.raceManager;
    }

}