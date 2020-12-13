package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.AddVehicleToVehicleBuffer;
import com.yaroslavm87.testtask01.ModelCommands.CreateNewVehicle;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;

public class AddNewVehicle extends ControllerCommand {

    VehicleType vehicleType;
    RaceManager raceManager;

    public AddNewVehicle(VehicleType vehicleType, RaceManager raceManager) {
        this.vehicleType = vehicleType;
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {

        CreateNewVehicle createNewVehicle = new CreateNewVehicle(vehicleType, raceManager);

        createNewVehicle.execute();

        new AddVehicleToVehicleBuffer(
                createNewVehicle.getCreatedVehicle(),
                raceManager.getVehicleBuffer()
        ).execute();
    }
}