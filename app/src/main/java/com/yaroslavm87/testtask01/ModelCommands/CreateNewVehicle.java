package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Creator.*;
import com.yaroslavm87.testtask01.Vehicle.*;

public class CreateNewVehicle extends ModelCommand {

    private VehicleType vehicleType;
    private Vehicle createdVehicle;
    private RaceManager raceManager;
    private Publisher publisher;

    public CreateNewVehicle(VehicleType vehicleType, RaceManager raceManager) {
        super();
        this.vehicleType = vehicleType;
        this.raceManager = raceManager;
        this.publisher = new Publisher();
    }

    @Override
    public void execute() {

        switch (vehicleType) {

//            case MOTORCYCLE:
//                createdVehicle = new MotorcycleCreator(raceManager, publisher).create();
//                super.markAsExecuted();

            case CAR:
                this.createdVehicle = new CarCreator(this.raceManager, this.publisher).create();
                new SetVehiclePunctureProbability(createdVehicle, 0.7).execute();
                //double speed = Math.random() * 50;
                new SetVehicleSpeed(createdVehicle, 60).execute();
                super.markAsExecuted();
                break;

//            case TRUCK:
//                createdVehicle = new TruckCreator(raceManager, publisher).create();
//                super.markAsExecuted();
        }
    }

    public Vehicle getCreatedVehicle() {
        return createdVehicle;
    }
}