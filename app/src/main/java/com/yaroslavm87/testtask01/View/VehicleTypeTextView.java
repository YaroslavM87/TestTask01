package com.yaroslavm87.testtask01.View;

import android.widget.TextView;

public class VehicleTypeTextView extends ActivityTextView {

    public VehicleTypeTextView(TextView textView) {
        super(textView);
    }

    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {

            if(updatedValue != "") {
                textViewValue = "Type of vehicle: ".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else {
            if(updatedValue != null) {
                textViewValue = "Type of vehicle: ".concat(updatedValue.toString());

            }  else {
                textViewValue = "";
            }
        }

        this.textView.setText(textViewValue);
    }
}