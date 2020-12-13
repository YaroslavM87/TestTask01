package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueDistanceTravelledToSubscriber extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueDistanceTravelledToSubscriber(
            Vehicle vehicleHavingValueDistanceTravelled,
            Subscriber valueDistanceTravelledReceiver
    ) {
        super();
        this.sender = vehicleHavingValueDistanceTravelled;
        this.receiver = valueDistanceTravelledReceiver;
    }

    @Override
    public void execute() {
        if(receiver != null) {
            receiver.receiveUpdate(this.sender.getDistanceTravelledInMeters());
            super.markAsExecuted();
        }
    }
}