package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueCurrentSpeedToSubscriberModelCommand extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueCurrentSpeedToSubscriberModelCommand(
            Vehicle vehicleHavingValueSpeed,
            Subscriber valueSpeedCurrentReceiver) {
        super();
        this.sender = vehicleHavingValueSpeed;
        this.receiver = valueSpeedCurrentReceiver;
    }

    @Override
    public void execute() {
        Double currentSpeed = this.sender.getCurrentSpeed();
        receiver.receiveUpdate(currentSpeed);
        super.markAsExecuted();
    }
}