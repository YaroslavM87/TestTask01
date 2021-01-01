package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleGotPuncture;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class MakeVehicleGetPunctureModelCommand extends ModelCommand {

    private Vehicle vehicleGotPuncture;

    public MakeVehicleGetPunctureModelCommand(Vehicle vehicleGotPuncture) {
        super();
        this.vehicleGotPuncture = vehicleGotPuncture;
    }

    @Override
    public void execute() {
        this.vehicleGotPuncture.changeState(new VehicleGotPuncture());
        super.markAsExecuted();
    }
}