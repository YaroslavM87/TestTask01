package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueMaxSpeedToSubscriberModelCommand extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueMaxSpeedToSubscriberModelCommand(
            Vehicle vehicleHavingValueSpeed,
            Subscriber valueMaxSpeedReceiver) {
        super();
        this.sender = vehicleHavingValueSpeed;
        this.receiver = valueMaxSpeedReceiver;
    }

    @Override
    public void execute() {
        Double maxSpeed = this.sender.getMaxSpeed();
        receiver.receiveUpdate(maxSpeed);
        super.markAsExecuted();
    }
}