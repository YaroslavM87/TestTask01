package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleFinished;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class MakeVehicleFinished extends ModelCommand {

    private Vehicle finishedVehicle;

    public MakeVehicleFinished(Vehicle finishedVehicle) {
        super();
        this.finishedVehicle = finishedVehicle;
    }

    @Override
    public void execute() {
        this.finishedVehicle.changeState(new VehicleFinished());
        super.markAsExecuted();
    }
}