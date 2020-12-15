package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.StateType;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.Vehicle.*;

public class AddVehicleToBufferModelCommand extends ModelCommand {

    private Vehicle vehicleToAdd;
    private RaceManager raceManager;

    public AddVehicleToBufferModelCommand(Vehicle vehicle, RaceManager raceManager) {
        super();
        this.vehicleToAdd = vehicle;
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {
        if(
                this.raceManager.getState().getType() == StateType.PRE_RACE
        )
        {
            this.raceManager.getVehicleBuffer().addVehicleToBuffer(this.vehicleToAdd);
            super.markAsExecuted();
        }
    }
}