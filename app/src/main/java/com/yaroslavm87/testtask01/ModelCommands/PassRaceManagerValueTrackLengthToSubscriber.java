package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class PassRaceManagerValueTrackLengthToSubscriber extends ModelCommand {

    private RaceManager sender;
    private Subscriber receiver;

    public PassRaceManagerValueTrackLengthToSubscriber(
            RaceManager raceManagerHavingValueTrackLength,
            Subscriber valueSpeedReceiver
    ) {
        super();
        this.sender = raceManagerHavingValueTrackLength;
        this.receiver = valueSpeedReceiver;
    }

    @Override
    public void execute() {
        Double trackLengthInKm = this.sender.getTrackLength();
        receiver.receiveUpdate(trackLengthInKm);
        super.markAsExecuted();
    }
}