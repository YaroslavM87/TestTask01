package com.yaroslavm87.testtask01.Vehicle.Creator;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservable;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleRaceManager;
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
        new SetVehicleRaceManager(newCar, super.raceManager).execute();
        new SetPublisherToObservable(newCar, super.publisher).execute();
        new ModelCommandSetVehicleState(newCar, new Stateless(newCar)).execute();
        return newCar;
    }
}