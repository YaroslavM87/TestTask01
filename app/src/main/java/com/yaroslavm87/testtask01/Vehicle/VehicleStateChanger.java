package com.yaroslavm87.testtask01.Vehicle;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Vehicle.States.*;

public class VehicleStateChanger {

    public VehicleStateChanger() {}

    public void setNextVehicleState(Vehicle vehicle, Event event) {

        VehicleState currentVehicleState = vehicle.getState();
        VehicleState nextVehicleState  = null;

        switch (currentVehicleState.getType()) {

            case STATELESS:
                if(event.getType() == EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED) {
                    nextVehicleState = new Initialized(vehicle);
                    break;
                }

            case INITIALIZED_AND_READY_TO_START:
                if(event.getType() == EventType.RACE_STARTED) {
                    nextVehicleState = new StateRaceForVehicle(vehicle);
                    break;
                }

            case RACE:
                if(event.getType() == EventType.VEHICLE_GOT_PUNCTURE) {
                    nextVehicleState = new GetPuncture(vehicle);
                    break;

                } else if (event.getType() == EventType.VEHICLE_FINISHED) {
                    nextVehicleState = new Finished(vehicle);
                    break;
                }

            case GOT_PUNCTURE:
                if(event.getType() == EventType.VEHICLE_RETURNED_TO_RACE) {
                    nextVehicleState = new StateRaceForVehicle(vehicle);
                    break;
                }
        }

        new ModelCommandSetVehicleState(vehicle, nextVehicleState).execute();
    }

//    private boolean checkIfVehicleStateDifferFromIntendedState(
//            VehicleState vehicleState, VehicleStateType vehicleStateType
//    ) {
//        return !(vehicleState.getType() == vehicleStateType);
//    }
}