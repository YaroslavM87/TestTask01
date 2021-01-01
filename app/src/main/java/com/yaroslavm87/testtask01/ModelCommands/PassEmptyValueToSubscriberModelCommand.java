package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassEmptyValueToSubscriberModelCommand extends ModelCommand {

    private Subscriber receiver;

    public PassEmptyValueToSubscriberModelCommand(
            Subscriber emptyValueReceiver) {
        super();
        this.receiver = emptyValueReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate(null);
        super.markAsExecuted();
    }
}