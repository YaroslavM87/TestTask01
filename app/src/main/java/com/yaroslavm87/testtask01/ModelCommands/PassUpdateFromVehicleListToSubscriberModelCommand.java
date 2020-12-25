package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class PassUpdateFromVehicleListToSubscriberModelCommand extends ModelCommand {

    private VehicleStartList sender;
    private Subscriber receiver;

    public PassUpdateFromVehicleListToSubscriberModelCommand(
            VehicleStartList vehicleStartList,
            Subscriber newVehicleAddedNotificationReceiver) {
        super();
        this.sender = vehicleStartList;
        this.receiver = newVehicleAddedNotificationReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate(this.sender);
        super.markAsExecuted();
    }
}