package com.yaroslavm87.testtask01.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.ArrayList;

public class AdapterForRecyclerViewStartList extends RecyclerView.Adapter<AdapterForRecyclerViewStartList.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{

        // implements View.OnClickListener

        private ActivityTextView VHVehicleType;
        private ActivityTextView VHVehicleSpeed;
        private ActivityTextView VHVehicleDistanceTravelled;
        private VehicleDistanceTravelledProgressBar progressBar;

        ViewHolder(
                @NonNull View itemView,
                int vehicleType,
                int vehicleSpeed,
                int vehicleDistanceTravelled
        ) {
            super(itemView);
            //itemView.setOnClickListener(this);
            this.VHVehicleType = new VehicleTypeTextView(itemView.findViewById(vehicleType));
            this.VHVehicleSpeed = new VehicleSpeedTextView(itemView.findViewById(vehicleSpeed));
            this.VHVehicleDistanceTravelled = new VehicleDistanceTravelledTextView(
                    itemView.findViewById(vehicleDistanceTravelled)
            );
            this.progressBar = new VehicleDistanceTravelledProgressBar(
                    itemView.findViewById(R.id.vehicleStartListVehicleDistanceTravelled)
            );
        }

//        @Override
//        public void onClick(View v) {
//            if (onEntryClickListener != null) {
//                onEntryClickListener.onEntryClick(v, getLayoutPosition());
//            }
//        }
    }

    private ArrayList<Vehicle> itemListForRecyclerView;
    //private OnEntryClickListener onEntryClickListener;
    private int layout;
    private int vehicleType;
    private int vehicleSpeed;
    private int vehicleDistanceTravelled;
    private Publisher raceManagerPublisher;

    public AdapterForRecyclerViewStartList(
            ArrayList<Vehicle> itemListForRecyclerView,
            int layout,
            int vehicleType,
            int vehicleSpeed,
            int vehicleDistanceTravelled,
            Publisher raceManagerPublisher
    ) {
        this.itemListForRecyclerView = itemListForRecyclerView;
        this.layout = layout;
        this.vehicleType = vehicleType;
        this.vehicleSpeed = vehicleSpeed;
        this.vehicleDistanceTravelled = vehicleDistanceTravelled;
        this.raceManagerPublisher = raceManagerPublisher;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(
                view,
                this.vehicleType,
                this.vehicleSpeed,
                this.vehicleDistanceTravelled
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicle vehicle = itemListForRecyclerView.get(position);
        Publisher publisher = itemListForRecyclerView.get(position).getPublisher();

        holder.VHVehicleType.receiveUpdate(vehicle.getVehicleType());
        publisher.subscribeForEvent(
                holder.VHVehicleType,
                EventType.VEHICLE_TYPE_CHANGED
        );

        holder.VHVehicleSpeed.receiveUpdate(vehicle.getCurrentSpeed());
        publisher.subscribeForEvent(
                holder.VHVehicleSpeed,
                EventType.VEHICLE_VALUE_CHANGED_SPEED
        );

        holder.VHVehicleDistanceTravelled.receiveUpdate(vehicle.getDistanceTravelledInMeters());
        publisher.subscribeForEvent(
                holder.VHVehicleDistanceTravelled,
                EventType.VEHICLE_VALUE_CHANGED_DISTANCE_TRAVELLED
        );


        holder.progressBar.getMaxProgressValue().receiveUpdate(
                (int) vehicle.getRaceManager().getTrackLength()
        );

        this.raceManagerPublisher.subscribeForEvent(
                holder.progressBar.getMaxProgressValue(),
                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
        );

        holder.progressBar.getCurrentProgressValue().receiveUpdate(
                vehicle.getDistanceTravelledInMeters()
        );

        publisher.subscribeForEvent(
                holder.progressBar.getCurrentProgressValue(),
                EventType.VEHICLE_VALUE_CHANGED_DISTANCE_TRAVELLED
        );
    }

    @Override
    public int getItemCount() {
        return itemListForRecyclerView.size();
    }

//    public interface OnEntryClickListener {
//        void onEntryClick(View view, int position);
//    }

//    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
//        this.onEntryClickListener = onEntryClickListener;
//    }
}