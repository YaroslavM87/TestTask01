package com.yaroslavm87.testtask01.Controller;
import com.yaroslavm87.testtask01.ModelCommands.SetVehicleMaxSpeedModelCommandModelCommand;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class SetVehicleMaxSpeedControllerCommand extends ControllerCommand {

    private Vehicle vehicle;
    private double changeForVehicleMaxSpeed;

    public SetVehicleMaxSpeedControllerCommand(Vehicle vehicle, double changeForVehicleMaxSpeed) {
        super();
        this.vehicle = vehicle;
        this.changeForVehicleMaxSpeed = changeForVehicleMaxSpeed;
    }

    @Override
    public void execute() {
        double result = changeForVehicleMaxSpeed + vehicle.getMaxSpeed();
        new SetVehicleMaxSpeedModelCommandModelCommand(vehicle, result).execute();
    }
}