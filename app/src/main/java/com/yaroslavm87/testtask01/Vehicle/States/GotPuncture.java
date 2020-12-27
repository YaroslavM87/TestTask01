package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.ModelCommands.ReturnVehicleToRaceModelCommand;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.Timer;
import java.util.TimerTask;

public class GotPuncture extends VehicleState {

    public GotPuncture(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    void setType() {
        super.stateType = VehicleStateType.GOT_PUNCTURE;
    }

    @Override
    public void performTaskDefinedByState() {
        startRepairingTimer();
    }

    private void startRepairingTimer() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                new ReturnVehicleToRaceModelCommand(vehicle).execute();
            }
        };

        Timer repairingTimer = new Timer();

        repairingTimer.schedule(timerTask, 4000);
    }
}