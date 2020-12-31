package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleBufferNewVehicleAdded;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleTypeChanged;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedMaxSpeed;
import com.yaroslavm87.testtask01.Notifications.Events.VehicleValueChangedPunctureProbability;
import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleBuffer;

public class PassVehicleAsValueToSubscriberModelCommand extends ModelCommand {

    private VehicleBuffer sender;
    private Subscriber receiver;

    public PassVehicleAsValueToSubscriberModelCommand(VehicleBuffer vehicleBuffer, Subscriber vehicleAsValueReceiver) {
        super();
        this.sender = vehicleBuffer;
        this.receiver = vehicleAsValueReceiver;
    }

    @Override
    public void execute() {
        receiver.receiveUpdate(sender.getVehicleFromBuffer());
        super.markAsExecuted();


//        if(event.getType() == EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED) {
//            sender.getPublisher().notifyEventHappened(sender, new VehicleBufferNewVehicleAdded());
////            sender.getPublisher().notifyEventHappened(sender.getVehicleFromBuffer(), new VehicleValueChangedMaxSpeed());
////            sender.getPublisher().notifyEventHappened(sender.getVehicleFromBuffer(), new VehicleValueChangedPunctureProbability());
//            //sender.getPublisher().notifyEventHappened(sender, new VehicleValueChangedAdditionalValue());
//        } else if(event.getType() == EventType.VEHICLE_BUFFER_VEHICLE_DELETED) {
//            sender.getPublisher().notifyEventHappened(null, new VehicleBufferNewVehicleAdded());
//        }
    }
}