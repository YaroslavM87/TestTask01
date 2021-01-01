package com.yaroslavm87.testtask01.Vehicle.Creator;

import com.yaroslavm87.testtask01.ModelCommands.SetPublisherToObservableModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleMaxSpeedModelCommandModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehiclePunctureProbabilityModelCommand;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleRaceManagerModelCommand;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Car;
import com.yaroslavm87.testtask01.Vehicle.ModelCommandSetVehicleState;
import com.yaroslavm87.testtask01.Vehicle.Motorcycle;
import com.yaroslavm87.testtask01.Vehicle.States.Stateless;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class MotoCreator extends VehicleCreator {

    public MotoCreator(RaceManager raceManager, Publisher publisher) {
        super(raceManager, publisher);
    }

    @Override
    public Vehicle create() {
        Motorcycle newMoto = new Motorcycle();
        new SetVehicleRaceManagerModelCommand(newMoto, super.raceManager).execute();
        new SetPublisherToObservableModelCommand(newMoto, super.publisher).execute();
        new ModelCommandSetVehicleState(newMoto, new Stateless(newMoto)).execute();
        new SetVehicleMaxSpeedModelCommandModelCommand(newMoto, 100).execute();
        new SetVehiclePunctureProbabilityModelCommand(newMoto, 0.7).execute();
        return newMoto;
    }
}