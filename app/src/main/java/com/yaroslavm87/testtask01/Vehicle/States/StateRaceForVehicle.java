package com.yaroslavm87.testtask01.Vehicle.States;

import com.yaroslavm87.testtask01.ModelCommands.*;
import com.yaroslavm87.testtask01.Vehicle.*;

import java.util.Timer;
import java.util.TimerTask;

public class StateRaceForVehicle extends VehicleState {

    private Timer raceTimer;
    private boolean vehicleGotPuncture;

    {
        vehicleGotPuncture = false;
    }

    public StateRaceForVehicle(Vehicle vehicle) {
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

                if(vehicleGotPuncture) {

                    if(checkIfVehicleHasSpeed()) {
                        decelerateVehicle();
                        calculateVehicleDistanceTraveled();

                    } else {
                        makeVehicleGetPuncture();
                    }

                } else {

                    if(checkIfVehicleIsAccelerating()) {
                        accelerateVehicle();
                    }

                    calculateVehicleDistanceTraveled();
                    performActionIfVehicleHasFinished();
                    decideIfVehicleGetPuncture();
                }
            }
        };

        raceTimer = new Timer();
        raceTimer.scheduleAtFixedRate(timerTask, 100, 100);
    }

    private boolean checkIfVehicleIsAccelerating() {
        return super.vehicle.getCurrentSpeed() < super.vehicle.getMaxSpeed();
    }

    private void accelerateVehicle() {
        double result = super.vehicle.getCurrentSpeed() + 1;
        new SetVehicleCurrentSpeedModelCommand(super.vehicle, result).execute();
    }

    private boolean checkIfVehicleHasSpeed() {
        return super.vehicle.getCurrentSpeed() > 0;
    }

    private void decelerateVehicle() {
        double result = super.vehicle.getCurrentSpeed() - 3;

        if(result > 0) {
            new SetVehicleCurrentSpeedModelCommand(super.vehicle, result).execute();

        } else {
            new SetVehicleCurrentSpeedModelCommand(super.vehicle, 0).execute();
        }
    }

    // calculates distance travelled for each 100ms
    //TODO: improve calculation accuracy
    private void calculateVehicleDistanceTraveled() {
        double speedInMetersPerSecond = super.vehicle.getCurrentSpeed() / 3.6;
        double distanceTraveled = super.vehicle.getDistanceTravelledInMeters()
                + (speedInMetersPerSecond / 10);
        new SetVehicleDistanceTravelledModelCommand(super.vehicle, distanceTraveled).execute();
    }

    private double calculateVehicleDistanceRemainedInMeters() {
        return super.vehicle.getRaceManager().getTrackLength() -
                super.vehicle.getDistanceTravelledInMeters();
    }

    //TODO: improve calculation accuracy
    private void decideIfVehicleGetPuncture() {
        double result = super.vehicle.getPunctureProbability() * Math.random();
        vehicleGotPuncture = result >= 0.695;
    }

    //TODO: split into two different methods
    private void performActionIfVehicleHasFinished() {
        if(
                super.vehicle.getDistanceTravelledInMeters() >=
                super.vehicle.getRaceManager().getTrackLength()
        ) {
            new SetVehicleDistanceTravelledModelCommand(
                    super.vehicle,
                    super.vehicle.getRaceManager().getTrackLength()
            ).execute(); // does not relate to the name of the method

            new MakeVehicleFinishedModelCommand(super.vehicle).execute();
            super.vehicle.getRaceManager().countVehicleAsFinished();
            raceTimer.cancel();
        }
    }

    private void makeVehicleGetPuncture() {
        calculateVehicleDistanceTraveled();
        new MakeVehicleGetPunctureModelCommand(super.vehicle).execute();
        raceTimer.cancel();
    }
}