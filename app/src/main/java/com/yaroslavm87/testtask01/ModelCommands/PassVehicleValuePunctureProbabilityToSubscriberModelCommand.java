package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValuePunctureProbabilityToSubscriberModelCommand extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValuePunctureProbabilityToSubscriberModelCommand(
            Vehicle vehicleHavingValuePunctureProbability,
            Subscriber valuePunctureProbabilityReceiver) {
        super();
        this.sender = vehicleHavingValuePunctureProbability;
        this.receiver = valuePunctureProbabilityReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate(this.sender.getPunctureProbability());
        super.markAsExecuted();
    }
}