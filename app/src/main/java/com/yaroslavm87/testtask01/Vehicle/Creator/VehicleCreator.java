package com.yaroslavm87.testtask01.Vehicle.Creator;

import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public abstract class VehicleCreator {

    RaceManager raceManager;
    Publisher publisher;

    public VehicleCreator(RaceManager raceManager, Publisher publisher) {
        this.raceManager = raceManager;
        this.publisher = publisher;
    }

    public abstract Vehicle create();
}