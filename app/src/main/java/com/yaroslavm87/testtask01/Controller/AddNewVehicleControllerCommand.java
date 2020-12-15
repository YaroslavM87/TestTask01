package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.AddVehicleToBufferModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.CreateNewVehicleModelCommand;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;

public class AddNewVehicleControllerCommand extends ControllerCommand {

    VehicleType vehicleType;
    RaceManager raceManager;

    public AddNewVehicleControllerCommand(VehicleType vehicleType, RaceManager raceManager) {
        this.vehicleType = vehicleType;
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {

        CreateNewVehicleModelCommand createNewVehicle = new CreateNewVehicleModelCommand(vehicleType, raceManager);

        createNewVehicle.execute();

        new AddVehicleToBufferModelCommand(
                createNewVehicle.getCreatedVehicle(),
                raceManager
        ).execute();
    }
}