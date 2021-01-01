package com.yaroslavm87.testtask01.Vehicle.Creator;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservableModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleMaxSpeedModelCommandModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehiclePunctureProbabilityModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleRaceManagerModelCommand;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Car;
import com.yaroslavm87.testtask01.Vehicle.ModelCommandSetVehicleState;
import com.yaroslavm87.testtask01.Vehicle.States.Stateless;
import com.yaroslavm87.testtask01.Vehicle.Truck;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class TruckCreator extends VehicleCreator {

    public TruckCreator(RaceManager raceManager, Publisher publisher) {
        super(raceManager, publisher);
    }

    @Override
    public Vehicle create() {
        Truck newTruck = new Truck();
        new SetVehicleRaceManagerModelCommand(newTruck, super.raceManager).execute();
        new SetPublisherToObservableModelCommand(newTruck, super.publisher).execute();
        new ModelCommandSetVehicleState(newTruck, new Stateless(newTruck)).execute();
        new SetVehicleMaxSpeedModelCommandModelCommand(newTruck, 60).execute();
        new SetVehiclePunctureProbabilityModelCommand(newTruck, 0.7).execute();
        return newTruck;
    }
}