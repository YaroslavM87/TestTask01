package com.yaroslavm87.testtask01.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.Vehicle.VehicleType;

import java.util.ArrayList;

public class AdapterForRecyclerViewVehicleTypes extends RecyclerView.Adapter<AdapterForRecyclerViewVehicleTypes.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ActivityTextView textViewForViewHolder;

        ViewHolder(@NonNull View itemView, int textView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewForViewHolder = new VehicleTypeTextView(itemView.findViewById(textView));
        }

        @Override
        public void onClick(View v) {
            if (onEntryClickListener != null) {
                onEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private ArrayList<VehicleType> itemListForRecyclerView;
    private OnEntryClickListener onEntryClickListener;
    private int layout;
    private int textView;

    public AdapterForRecyclerViewVehicleTypes(
            ArrayList<VehicleType> itemListForRecyclerView,
            int layout,
            int textView
    ) {
        this.itemListForRecyclerView = itemListForRecyclerView;
        this.layout = layout;
        this.textView = textView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view, textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewForViewHolder.getTextView().setText(itemListForRecyclerView.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return itemListForRecyclerView.size();
    }

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        this.onEntryClickListener = onEntryClickListener;
    }
}