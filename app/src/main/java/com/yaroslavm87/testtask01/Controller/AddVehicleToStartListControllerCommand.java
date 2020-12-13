package com.yaroslavm87.testtask01.Controller;

import com.yaroslavm87.testtask01.ModelCommands.AddVehicleToStartListModelCommand;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;

public class AddVehicleToStartListControllerCommand extends ControllerCommand {

    private VehicleBuffer vehicleBuffer;
    private VehicleStartList vehicleStartList;

    public AddVehicleToStartListControllerCommand(VehicleBuffer vehicleBuffer, VehicleStartList vehicleStartList) {
        super();
        this.vehicleBuffer = vehicleBuffer;
        this.vehicleStartList = vehicleStartList;
    }

    @Override
    public void execute() {

        if(this.vehicleBuffer.getVehicleFromBuffer() != null) {

            new AddVehicleToStartListModelCommand(
                    this.vehicleBuffer.getVehicleFromBuffer(),
                    this.vehicleStartList
            ).execute();
            this.vehicleBuffer.deleteVehicleFromBuffer();
        }
    }
}