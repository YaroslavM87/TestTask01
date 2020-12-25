package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassEmptyValueToSubscriberModelCommand extends ModelCommand {

    private Subscriber receiver;

    public PassEmptyValueToSubscriberModelCommand(
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