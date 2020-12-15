package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.ModelCommands.*;
import com.yaroslavm87.testtask01.Vehicle.*;

import java.util.Timer;
import java.util.TimerTask;

public class Race extends VehicleState {

    private Timer raceTimer;

    public Race(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    void setType() {
        super.stateType = VehicleStateType.RACE;
    }

    @Override
    public void performTaskDefinedByState() {
        startRaceTimer();
    }

//    public void cancelTimer() {
//        raceTimer.cancel();
//    }

    private void startRaceTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                calculateVehicleDistanceTraveled();
                checkIfVehicleHasFinished();
                decideIfVehicleGetPuncture();
            }
        };
        raceTimer = new Timer();
        raceTimer.scheduleAtFixedRate(timerTask, 100, 100);
    }

    // calculates distance travelled for each 100ms
    //TODO: improve calculation accuracy
    private void calculateVehicleDistanceTraveled() {
        double speedInMetersPerSecond = super.vehicle.getCurrentSpeed() / 3.6;
        double distanceTraveled = super.vehicle.getDistanceTravelledInMeters()
                + (speedInMetersPerSecond / 10);
        new SetVehicleDistanceTravelled(super.vehicle, distanceTraveled).execute();
    }

    private double calculateVehicleDistanceRemainedInMeters() {
        return super.vehicle.getRaceManager().getTrackLength() -
                super.vehicle.getDistanceTravelledInMeters();
    }

    //TODO: improve calculation accuracy
    private void decideIfVehicleGetPuncture() {
        double result = super.vehicle.getPunctureProbability() * Math.random();
        if(result >= 0.695) {
            new MakeVehicleGetPuncture(super.vehicle).execute();
            raceTimer.cancel();
        }
    }

    //TODO: split into two different methods
    private void checkIfVehicleHasFinished() {
        if(
                super.vehicle.getDistanceTravelledInMeters() >=
                super.vehicle.getRaceManager().getTrackLength()
        ) {
            new SetVehicleDistanceTravelled(
                    super.vehicle,
                    super.vehicle.getRaceManager().getTrackLength()
            ).execute(); // does not relate to the name of the method

            new MakeVehicleFinished(super.vehicle).execute();

            raceTimer.cancel();
        }
    }


}