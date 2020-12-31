package com.yaroslavm87.testtask01.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.ArrayList;

public class AdapterForRecyclerViewRaceList extends RecyclerView.Adapter<AdapterForRecyclerViewRaceList.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ActivityTextView holderVehicleType;
        private ActivityTextView holderVehicleCurrentSpeedAndStatus;
        private ActivityTextView holderVehicleDistanceTravelled;
        private ActivityTextView holderVehicleFinishTime;
        private OnEventTextViewColorChanger onEventTextViewColorChanger;

        ViewHolder(
                @NonNull View itemView,
                int vehicleType,
                int vehicleCurrentSpeedAndStatus,
                int vehicleDistanceTravelled,
                int vehicleFinishTime
        ) {

            super(itemView);

            this.holderVehicleType = new TextViewVehicleType(itemView.findViewById(vehicleType));

            this.holderVehicleCurrentSpeedAndStatus = new TextViewVehicleSpeedAndStatus(
                    itemView.findViewById(vehicleCurrentSpeedAndStatus)
            );

            this.holderVehicleDistanceTravelled = new TextViewVehicleDistanceTravelled(
                    itemView.findViewById(vehicleDistanceTravelled)
            );

            this.holderVehicleFinishTime = new TextViewRaceTimer(
                    itemView.findViewById(vehicleFinishTime)
            );

            this.holderVehicleFinishTime.getTextView().setText("- - -");

            this.onEventTextViewColorChanger = new OnEventTextViewColorChanger();

            this.onEventTextViewColorChanger.addTextViewToList(
                    this.holderVehicleType.getTextView(),
                    this.holderVehicleCurrentSpeedAndStatus.getTextView(),
                    this.holderVehicleDistanceTravelled.getTextView(),
                    this.holderVehicleFinishTime.getTextView()
            );
        }
    }

    private ArrayList<Vehicle> itemListForRecyclerView;
    private int layout;
    private int vehicleType;
    private int vehicleCurrentSpeedAndStatus;
    private int vehicleDistanceTravelled;
    private int vehicleFinishTime;

    public AdapterForRecyclerViewRaceList(
            ArrayList<Vehicle> itemListForRecyclerView,
            int layout,
            int vehicleType,
            int vehicleCurrentSpeedAndStatus,
            int vehicleDistanceTravelled,
            int vehicleFinishTime
    ) {
        this.itemListForRecyclerView = itemListForRecyclerView;
        this.layout = layout;
        this.vehicleType = vehicleType;
        this.vehicleCurrentSpeedAndStatus = vehicleCurrentSpeedAndStatus;
        this.vehicleDistanceTravelled = vehicleDistanceTravelled;
        this.vehicleFinishTime = vehicleFinishTime;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(
                view,
                this.vehicleType,
                this.vehicleCurrentSpeedAndStatus,
                this.vehicleDistanceTravelled,
                this.vehicleFinishTime
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicle vehicle = itemListForRecyclerView.get(position);
        Publisher vehiclePublisher = vehicle.getPublisher();

        holder.holderVehicleType.receiveUpdate(vehicle.getVehicleType());

        holder.holderVehicleCurrentSpeedAndStatus.receiveUpdate(vehicle.getCurrentSpeed());
        vehiclePublisher.subscribeForEvent(
                holder.holderVehicleCurrentSpeedAndStatus,
                EventType.VEHICLE_VALUE_CHANGED_CURRENT_SPEED,
                EventType.VEHICLE_VALUE_CHANGED_STATE
        );

        holder.holderVehicleDistanceTravelled.receiveUpdate(vehicle.getDistanceTravelledInMeters());
        vehiclePublisher.subscribeForEvent(
                holder.holderVehicleDistanceTravelled,
                EventType.VEHICLE_VALUE_CHANGED_DISTANCE_TRAVELLED
        );

        vehiclePublisher.subscribeForEvent(
                holder.holderVehicleFinishTime,
                EventType.VEHICLE_VALUE_CHANGED_FINISH_TIME
        );

        vehiclePublisher.subscribeForEvent(
                holder.onEventTextViewColorChanger,
                EventType.VEHICLE_VALUE_CHANGED_STATE
        );

        holder.onEventTextViewColorChanger.receiveUpdate(vehicle.getState());
    }

    @Override
    public int getItemCount() {
        return itemListForRecyclerView.size();
    }
}