package com.yaroslavm87.testtask01.Vehicle.Creator;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservableModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleMaxSpeedModelCommandModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehiclePunctureProbabilityModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleRaceManagerModelCommand;
import com.yaroslavm87.testtask01.Vehicle.ModelCommandSetVehicleState;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Car;
import com.yaroslavm87.testtask01.Vehicle.States.Stateless;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class CarCreator extends VehicleCreator {

    public CarCreator(RaceManager raceManager, Publisher publisher) {
        super(raceManager, publisher);
    }

    @Override
    public Vehicle create() {
        Car newCar = new Car();
        new SetVehicleRaceManagerModelCommand(newCar, super.raceManager).execute();
        new SetPublisherToObservableModelCommand(newCar, super.publisher).execute();
        new ModelCommandSetVehicleState(newCar, new Stateless(newCar)).execute();
        new SetVehicleMaxSpeedModelCommandModelCommand(newCar, 80).execute();
        new SetVehiclePunctureProbabilityModelCommand(newCar, 0.7).execute();
        return newCar;
    }
}