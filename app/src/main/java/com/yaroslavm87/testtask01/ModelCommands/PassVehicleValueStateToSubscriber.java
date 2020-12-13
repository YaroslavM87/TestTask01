package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class PassVehicleValueStateToSubscriber extends ModelCommand {

    private Vehicle sender;
    private Subscriber receiver;

    public PassVehicleValueStateToSubscriber(
            Vehicle vehicleHavingValueState,
            Subscriber valueStateReceiver
    ) {
        super();
        this.sender = vehicleHavingValueState;
        this.receiver = valueStateReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate(this.sender.getState());
        super.markAsExecuted();
    }
}