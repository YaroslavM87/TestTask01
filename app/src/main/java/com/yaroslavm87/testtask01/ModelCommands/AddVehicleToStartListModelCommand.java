package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleStartListNewVehicleAdded;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.*;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleStateType;

public class AddVehicleToStartListModelCommand extends ModelCommand {

    private Vehicle vehicleToAdd;
    private VehicleStartList vehicleStartList;

    public AddVehicleToStartListModelCommand(Vehicle vehicle, VehicleStartList vehicleStartList) {
        super();
        this.vehicleToAdd = vehicle;
        this.vehicleStartList = vehicleStartList;
    }

    @Override
    public void execute() {

        if(
                this.vehicleToAdd.getCurrentSpeed() != 0 &
                this.vehicleToAdd.getPunctureProbability() != 0 &
                this.vehicleToAdd.getState().getType() == VehicleStateType.STATELESS
        )
        {
            this.vehicleStartList.addVehicleToStartList(this.vehicleToAdd);
            this.vehicleToAdd.changeState(new VehicleStartListNewVehicleAdded());
                    //;
            super.markAsExecuted();
        }
    }
}