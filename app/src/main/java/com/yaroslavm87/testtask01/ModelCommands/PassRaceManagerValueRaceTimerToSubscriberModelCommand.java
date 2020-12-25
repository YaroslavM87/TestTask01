package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;

public class PassRaceManagerValueRaceTimerToSubscriberModelCommand extends ModelCommand {

    private RaceManager.RaceTimer sender;
    private Subscriber receiver;

    public PassRaceManagerValueRaceTimerToSubscriberModelCommand(
            RaceManager.RaceTimer raceTimerHavingValueRaceTimer,
            Subscriber valueRaceTimerReceiver
    ) {
        super();
        this.sender = raceTimerHavingValueRaceTimer;
        this.receiver = valueRaceTimerReceiver;
    }

    @Override
    public void execute() {
        Double raceTimerValue = this.sender.getRaceTimerValue();
        receiver.receiveUpdate(raceTimerValue);
        super.markAsExecuted();
    }
}