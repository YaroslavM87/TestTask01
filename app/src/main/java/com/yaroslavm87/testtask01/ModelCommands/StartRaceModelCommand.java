package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.RaceStarted;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.Timer;
import java.util.TimerTask;

public class StartRaceModelCommand extends ModelCommand {

    private RaceManager raceManager;

    public StartRaceModelCommand(RaceManager raceManager) {
        super();
        this.raceManager = raceManager;
    }

    //TODO: find another way for start delay
    @Override
    public void execute() {

        if(this.raceManager.getVehicleStartList().getList().size() > 1) {
            Event raceStarted = new RaceStarted();

            raceManager.raceTimerCount(5000);

            TimerTask timerTask1 = new TimerTask(){
                @Override
                public void run() {

                    if(raceManager.getRaceTimer().getRaceTimerValue() > 0L) {
                        raceManager.raceTimerCount(-1000);

                    } else {
                        raceManager.changeState(raceStarted);

                        for(Vehicle vehicleStarted : raceManager.getVehicleStartList().getList()) {
                            vehicleStarted.changeState(raceStarted);
                        }
                    }
                }
            };

            Timer timer1 = new Timer();
            timer1.scheduleAtFixedRate(timerTask1, 1000, 1000);

            TimerTask timerTask2 = new TimerTask(){
                @Override
                public void run() {
                    timer1.cancel();
                    raceManager.changeState(raceStarted);
                    for(Vehicle vehicleStarted : raceManager.getVehicleStartList().getList()) {
                        vehicleStarted.changeState(raceStarted);
                    }
                }
            };

            Timer timer2 = new Timer();
            timer2.schedule(timerTask2, 5000);

            super.markAsExecuted();
        }
    }
}