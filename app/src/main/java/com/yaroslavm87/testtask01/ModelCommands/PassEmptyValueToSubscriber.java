package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassEmptyValueToSubscriber extends ModelCommand {

    private Subscriber receiver;

    public PassEmptyValueToSubscriber(
            Subscriber valueSpeedReceiver) {
        super();
        this.receiver = valueSpeedReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate("");
        super.markAsExecuted();
    }
}