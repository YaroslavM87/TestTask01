package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleStateType;

import java.util.ArrayList;
import java.util.List;

public class TextViewVehicleSpeedAndStatus extends ActivityTextView {

    public TextViewVehicleSpeedAndStatus(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object value) {

        String textViewValue = "";

        if (value instanceof VehicleState) {
            VehicleStateType vehicleStateType = ((VehicleState) value).getType();

            if(vehicleStateType == VehicleStateType.GOT_PUNCTURE) {
                textViewValue = "PUNCTURE";

            } else if(vehicleStateType == VehicleStateType.FINISHED) {
                textViewValue = "FINISHED";
            }

        } else if(value instanceof Double) {
            double d = (Double) value;

            if(d >= 0.0) {
                textViewValue = String.format("%3.1f", d);
            }

        } else {
            if(value != null) {
                textViewValue = "".concat(value.toString());
            }
        }

        this.textView.setText(textViewValue);
    }
}
