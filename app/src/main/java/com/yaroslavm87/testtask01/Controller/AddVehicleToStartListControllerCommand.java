package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.AddVehicleToStartListModelCommand;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;

public class AddVehicleToStartListControllerCommand extends ControllerCommand {

    private RaceManager raceManager;

    public AddVehicleToStartListControllerCommand(RaceManager raceManager) {
        super();
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        new AddVehicleToStartListModelCommand(this.raceManager).execute();
    }
}