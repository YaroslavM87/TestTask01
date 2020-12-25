package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleReturnedToRace;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class ReturnVehicleToRaceModelCommand extends ModelCommand {

    private Vehicle vehicleReturnedToRace;

    public ReturnVehicleToRaceModelCommand(Vehicle vehicleReturnedToRace) {
        super();
        this.vehicleReturnedToRace = vehicleReturnedToRace;
    }

    @Override
    public void execute() {
        this.vehicleReturnedToRace.changeState(new VehicleReturnedToRace());
        super.markAsExecuted();
    }
}