package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueSpeedToSubscriber extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueSpeedToSubscriber(
            Vehicle vehicleHavingValueSpeed,
            Subscriber valueSpeedReceiver) {
        super();
        this.sender = vehicleHavingValueSpeed;
        this.receiver = valueSpeedReceiver;
    }

    @Override
    public void execute() {
        Double currentSpeed = this.sender.getCurrentSpeed();
        receiver.receiveUpdate(currentSpeed);
        super.markAsExecuted();
    }
}