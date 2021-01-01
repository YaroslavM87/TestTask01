package com.yaroslavm87.testtask01.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;

import java.util.ArrayList;

public class AdapterForRecyclerViewVehicleTypes extends RecyclerView.Adapter<AdapterForRecyclerViewVehicleTypes.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ActivityTextView textViewVehicleType;


        ViewHolder(@NonNull View itemView, int textViewVehicleType) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.textViewVehicleType = new TextViewVehicleType(itemView.findViewById(textViewVehicleType));
        }

        @Override
        public void onClick(View v) {
            if (onEntryClickListener != null) {
                onEntryClickListener.onEntryClick(getLayoutPosition());
            }
        }
    }

    private ArrayList<VehicleType> itemListForRecyclerView;
    private OnEntryClickListener onEntryClickListener;
    private int layout;
    private int textViewVehicleType;

    public AdapterForRecyclerViewVehicleTypes(
            ArrayList<VehicleType> itemListForRecyclerView,
            int layout,
            int textViewVehicleType
    ) {
        this.itemListForRecyclerView = itemListForRecyclerView;
        this.layout = layout;
        this.textViewVehicleType = textViewVehicleType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view, this.textViewVehicleType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch(itemListForRecyclerView.get(position)) {
            case TRUCK:
                holder.textViewVehicleType.getTextView().setBackgroundResource(
                        R.drawable.background_text_view_vehicle_available_truck
                );
                break;

            case CAR:
                holder.textViewVehicleType.getTextView().setBackgroundResource(
                        R.drawable.background_text_view_vehicle_available_car
                );
                break;

            case MOTORCYCLE:
                holder.textViewVehicleType.getTextView().setBackgroundResource(
                        R.drawable.background_text_view_vehicle_available_moto
                );
                break;
        }

        holder.textViewVehicleType.getTextView().setText(
                itemListForRecyclerView.get(position).toString()
        );
    }

    @Override
    public int getItemCount() {
        return itemListForRecyclerView.size();
    }

    public interface OnEntryClickListener {
        void onEntryClick(int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        this.onEntryClickListener = onEntryClickListener;
    }
}