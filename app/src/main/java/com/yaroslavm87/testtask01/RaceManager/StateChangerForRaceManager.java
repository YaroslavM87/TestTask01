package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.Notifications.Events.Event;

class StateChangerForRaceManager {

    void setNextState(RaceManager raceManager, Event event) {

        State nextRaceManagerState = null;

        switch(event.getType()) {

            case RACE_STARTED:
                nextRaceManagerState = new StateRaceForRaceManager(raceManager);
                raceManager.getPublisher().notifyEventHappened(event);
                break;

            case RACE_ENDED:
                nextRaceManagerState = new StatePostRace(raceManager);
                raceManager.getPublisher().notifyEventHappened(event);
                break;
        }

        new SetRaceManagerStateModelCommand(raceManager, nextRaceManagerState).execute();
    }
}