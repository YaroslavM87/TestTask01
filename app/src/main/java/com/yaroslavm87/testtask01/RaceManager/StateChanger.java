package com.yaroslavm87.testtask01.RaceManager;

import com.yaroslavm87.testtask01.Notifications.Events.Event;

class StateChanger {

    void setNextState(RaceManager raceManager, Event event) {

        State nextRaceManagerState = null;

        switch(event.getType()) {

            case RACE_STARTED:
                nextRaceManagerState = new StateRace(raceManager);
                break;

            case RACE_ENDED:
                nextRaceManagerState = new StatePostRace(raceManager);
                break;
        }

        new ModelCommandSetRaceManagerState(raceManager, nextRaceManagerState).execute();
    }
}