package com.yaroslavm87.testtask01.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;

import java.util.ArrayList;

public class AdapterForRecyclerViewStartList extends RecyclerView.Adapter<AdapterForRecyclerViewStartList.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{

        // implements View.OnClickListener

        private ActivityTextView VHVehicleType;
        private ActivityTextView VHVehicleMaxSpeed;

        ViewHolder(
                @NonNull View itemView,
                int vehicleType,
                int vehicleMaxSpeed
        ) {
            super(itemView);
            //itemView.setOnClickListener(this);
            this.VHVehicleType = new TextViewVehicleType(itemView.findViewById(vehicleType));
            this.VHVehicleMaxSpeed = new TextViewVehicleSpeed(itemView.findViewById(vehicleMaxSpeed));
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
    private int vehicleMaxSpeed;

    public AdapterForRecyclerViewStartList(
            ArrayList<Vehicle> itemListForRecyclerView,
            int layout,
            int vehicleType,
            int vehicleMaxSpeed
    ) {
        this.itemListForRecyclerView = itemListForRecyclerView;
        this.layout = layout;
        this.vehicleType = vehicleType;
        this.vehicleMaxSpeed = vehicleMaxSpeed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(
                view,
                this.vehicleType,
                this.vehicleMaxSpeed
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = itemListForRecyclerView.get(position);
        holder.VHVehicleType.receiveUpdate(vehicle.getVehicleType());
        holder.VHVehicleMaxSpeed.receiveUpdate(vehicle.getMaxSpeed());
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