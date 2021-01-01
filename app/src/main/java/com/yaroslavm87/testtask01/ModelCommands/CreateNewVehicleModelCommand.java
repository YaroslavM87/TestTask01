package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Creator.*;
import com.yaroslavm87.testtask01.Vehicle.*;

public class CreateNewVehicleModelCommand extends ModelCommand {

    private VehicleType vehicleType;
    private Vehicle createdVehicle;
    private RaceManager raceManager;
    private Publisher publisher;

    public CreateNewVehicleModelCommand(VehicleType vehicleType, RaceManager raceManager) {
        super();
        this.vehicleType = vehicleType;
        this.raceManager = raceManager;
        this.publisher = new Publisher();
    }

    @Override
    public void execute() {

        switch (vehicleType) {

            case MOTORCYCLE:
                this.createdVehicle = new MotoCreator(this.raceManager, this.publisher).create();
                super.markAsExecuted();
                break;

            case CAR:
                this.createdVehicle = new CarCreator(this.raceManager, this.publisher).create();
                super.markAsExecuted();
                break;

            case TRUCK:
                this.createdVehicle = new TruckCreator(raceManager, publisher).create();
                super.markAsExecuted();
                break;
        }
    }

    public Vehicle getCreatedVehicle() {
        return createdVehicle;
    }
}