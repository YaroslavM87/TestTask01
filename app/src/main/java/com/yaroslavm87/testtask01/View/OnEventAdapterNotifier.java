package com.yaroslavm87.testtask01.View;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.RaceManager.VehicleStartList;

public class OnEventAdapterNotifier implements Subscriber {

    private AdapterForRecyclerViewStartList adapterForRecyclerViewStartList;

    public OnEventAdapterNotifier(AdapterForRecyclerViewStartList adapterForRecyclerViewStartList) {
        this.adapterForRecyclerViewStartList = adapterForRecyclerViewStartList;
    }

    @Override
    public void receiveUpdate(Object value) {

        if (value instanceof VehicleStartList) {
            VehicleStartList vehicleStartList = (VehicleStartList) value;

            if (vehicleStartList.getCurrentEventType() != null) {
                switch (vehicleStartList.getCurrentEventType()) {

                    case VEHICLE_START_LIST_NEW_VEHICLE_ADDED:
                        int size = vehicleStartList.getList().size();
                        if (size > 0) {
                            adapterForRecyclerViewStartList.notifyItemInserted(size - 1);
                        }
                        break;

                    case VEHICLE_START_LIST_VEHICLE_DELETED:
                        adapterForRecyclerViewStartList.notifyItemRemoved(
                                vehicleStartList.getIndexOfVehicleBeingRemoved()
                        );
                        break;
                }

            }
        }
    }
}