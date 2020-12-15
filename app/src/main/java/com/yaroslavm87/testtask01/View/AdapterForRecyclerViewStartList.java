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

public class AdapterForRecyclerViewStartList extends RecyclerView.Adapter<AdapterForRecyclerViewStartList.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{

        // implements View.OnClickListener

        private ActivityTextView VHVehicleType;
        private ActivityTextView VHVehicleSpeed;
        private ActivityTextView VHVehicleDistanceTravelled;

        ViewHolder(
                @NonNull View itemView,
                int vehicleType,
                int vehicleSpeed,
                int vehicleDistanceTravelled
        ) {
            super(itemView);
            //itemView.setOnClickListener(this);
            this.VHVehicleType = new VehicleTypeTextView(itemView.findViewById(vehicleType));
            this.VHVehicleSpeed = new TextViewVehicleSpeed(itemView.findViewById(vehicleSpeed));
            this.VHVehicleDistanceTravelled = new VehicleDistanceTravelledTextView(
                    itemView.findViewById(vehicleDistanceTravelled)
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
        Publisher vehiclePublisher = vehicle.getPublisher();

        holder.VHVehicleType.receiveUpdate(vehicle.getVehicleType());
//        vehiclePublisher.subscribeForEvent(
//                holder.VHVehicleType,
//                EventType.VEHICLE_TYPE_CHANGED
//        );

        holder.VHVehicleSpeed.receiveUpdate(vehicle.getCurrentSpeed());
        vehiclePublisher.subscribeForEvent(
                holder.VHVehicleSpeed,
                EventType.VEHICLE_VALUE_CHANGED_CURRENT_SPEED
        );

        holder.VHVehicleDistanceTravelled.receiveUpdate(vehicle.getDistanceTravelledInMeters());
        vehiclePublisher.subscribeForEvent(
                holder.VHVehicleDistanceTravelled,
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