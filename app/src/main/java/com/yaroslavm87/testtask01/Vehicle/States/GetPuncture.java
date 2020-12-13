package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.ModelCommands.ReturnVehicleToRace;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.Timer;
import java.util.TimerTask;

public class GetPuncture extends VehicleState {

    public GetPuncture(Vehicle vehicle) {
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
                new ReturnVehicleToRace(vehicle).execute();
            }
        };
        Timer repairingTimer = new Timer();
        repairingTimer.schedule(timerTask, 4000);
    }
}