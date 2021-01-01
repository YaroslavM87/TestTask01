package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueFinishTimeToSubscriber extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueFinishTimeToSubscriber(
            Vehicle vehicleHavingValueFinishTime,
            Subscriber valueFinishTimeReceiver
    ) {
        super();
        this.sender = vehicleHavingValueFinishTime;
        this.receiver = valueFinishTimeReceiver;
    }

    @Override
    public void execute() {
        if(receiver != null) {
            receiver.receiveUpdate(this.sender.getFinishTime());
            super.markAsExecuted();
        }
    }
}