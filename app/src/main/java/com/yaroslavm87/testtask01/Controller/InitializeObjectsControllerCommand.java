package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservableModelCommand;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class InitializeObjectsControllerCommand extends ControllerCommand {

    private RaceManager raceManager;

    @Override
    public void execute() {
        this.raceManager = new RaceManager();

        Publisher vehicleRaceManagerPublisher = new Publisher();
        new SetPublisherToObservableModelCommand(this.raceManager, vehicleRaceManagerPublisher).execute();

        Publisher vehicleBufferPublisher = new Publisher();
        new SetPublisherToObservableModelCommand(this.raceManager.getVehicleBuffer(), vehicleBufferPublisher).execute();

        Publisher vehicleStartListPublisher = new Publisher();
        new SetPublisherToObservableModelCommand(this.raceManager.getVehicleStartList(), vehicleStartListPublisher).execute();
    }

    public RaceManager getRaceManager() {
        return this.raceManager;
    }

}