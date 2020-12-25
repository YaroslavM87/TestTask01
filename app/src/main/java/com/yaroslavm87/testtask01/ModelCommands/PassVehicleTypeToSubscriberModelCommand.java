package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleTypeToSubscriberModelCommand extends ModelCommand {

    private Vehicle senderVehicle;
    private VehicleBuffer senderVehicleBuffer;

    private Subscriber receiver;

    public PassVehicleTypeToSubscriberModelCommand(
            Vehicle vehicleHavingType,
            Subscriber vehicleTypeReceiver) {
        super();
        this.senderVehicle = vehicleHavingType;
        this.receiver = vehicleTypeReceiver;
    }

    public PassVehicleTypeToSubscriberModelCommand(
            VehicleBuffer vehicleBuffer,
            Subscriber vehicleTypeReceiver) {
        super();
        this.senderVehicleBuffer = vehicleBuffer;
        this.receiver = vehicleTypeReceiver;
    }

    @Override
    public void execute() {
        if(senderVehicle != null) {
            receiver.receiveUpdate(this.senderVehicle);
        } else if(senderVehicleBuffer != null) {
            receiver.receiveUpdate(this.senderVehicleBuffer);
        }

        super.markAsExecuted();
    }
}