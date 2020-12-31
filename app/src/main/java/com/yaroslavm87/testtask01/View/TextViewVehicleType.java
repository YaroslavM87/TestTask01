package com.yaroslavm87.testtask01.View;

import android.widget.TextView;

import com.yaroslavm87.testtask01.Vehicle.VehicleType;

public class TextViewVehicleType extends ActivityTextView {

    public TextViewVehicleType(TextView textView) {
        super(textView);
    }

    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue = "";

        if (updatedValue instanceof String) {

            if(updatedValue != "") {
                textViewValue = "".concat((String)updatedValue);
            }

        } else if(updatedValue instanceof VehicleType) {
            textViewValue = "".concat(updatedValue.toString());

        } else {
            if(updatedValue != null) {
                textViewValue = "".concat(updatedValue.toString());
            }
        }

        super.textView.setText(textViewValue);
    }
}