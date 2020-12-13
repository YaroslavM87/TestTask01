package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleGotPuncture;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class MakeVehicleGetPuncture extends ModelCommand {

    private Vehicle vehicleGotPuncture;

    public MakeVehicleGetPuncture(Vehicle vehicleGotPuncture) {
        super();
        this.vehicleGotPuncture = vehicleGotPuncture;
    }

    @Override
    public void execute() {
        this.vehicleGotPuncture.changeState(new VehicleGotPuncture());
        super.markAsExecuted();
    }
}