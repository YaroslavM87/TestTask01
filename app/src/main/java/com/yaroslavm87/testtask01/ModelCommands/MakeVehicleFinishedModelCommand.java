package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.VehicleFinished;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class MakeVehicleFinishedModelCommand extends ModelCommand {

    private Vehicle finishedVehicle;

    public MakeVehicleFinishedModelCommand(Vehicle finishedVehicle) {
        super();
        this.finishedVehicle = finishedVehicle;
    }

    @Override
    public void execute() {
        this.finishedVehicle.changeState(new VehicleFinished());
        super.markAsExecuted();
    }
}