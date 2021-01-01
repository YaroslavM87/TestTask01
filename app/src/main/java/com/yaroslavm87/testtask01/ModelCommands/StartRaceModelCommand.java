package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.RaceStarted;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class StartRaceModelCommand extends ModelCommand {

    private RaceManager raceManager;

    public StartRaceModelCommand(RaceManager raceManager) {
        super();
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {

        if(this.raceManager.getVehicleStartList().getList().size() > 1) {
            Event raceStarted = new RaceStarted();

            this.raceManager.changeState(raceStarted);

            for(Vehicle vehicleStarted : this.raceManager.getVehicleStartList().getList()) {
                vehicleStarted.changeState(raceStarted);
            }

            super.markAsExecuted();
        }
    }
}