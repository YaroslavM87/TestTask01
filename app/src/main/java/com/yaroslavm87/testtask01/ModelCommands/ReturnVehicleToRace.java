package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleReturnedToRace;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class ReturnVehicleToRace extends ModelCommand {

    private Vehicle vehicleReturnedToRace;

    public ReturnVehicleToRace(Vehicle vehicleReturnedToRace) {
        super();
        this.vehicleReturnedToRace = vehicleReturnedToRace;
    }

    @Override
    public void execute() {
        this.vehicleReturnedToRace.changeState(new VehicleReturnedToRace());
        super.markAsExecuted();
    }
}