package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleStartListNewVehicleAdded;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.StateType;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.*;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleStateType;

public class AddVehicleToStartListModelCommand extends ModelCommand {

    private RaceManager raceManager;

    public AddVehicleToStartListModelCommand(RaceManager raceManager) {
        super();
        this.raceManager = raceManager;
    }

    @Override
    public void execute() {

        if(this.raceManager.getVehicleBuffer().getVehicleFromBuffer() != null) {

            Vehicle vehicleToAdd = this.raceManager.getVehicleBuffer().getVehicleFromBuffer();

            if(
                    this.raceManager.getState().getType() == StateType.PRE_RACE &
                    this.raceManager.getTrackLength() > 0 &
                    vehicleToAdd.getMaxSpeed() != 0 &
                    vehicleToAdd.getPunctureProbability() != 0 &
                    vehicleToAdd.getState().getType() == VehicleStateType.STATELESS
            )
            {
                this.raceManager.getVehicleStartList().addVehicleToStartList(vehicleToAdd);

                vehicleToAdd.changeState(new VehicleStartListNewVehicleAdded());

                this.raceManager.getVehicleBuffer().deleteVehicleFromBuffer();

                super.markAsExecuted();
            }
        }
    }
}