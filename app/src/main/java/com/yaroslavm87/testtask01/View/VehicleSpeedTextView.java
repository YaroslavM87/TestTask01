package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class VehicleSpeedTextView extends ActivityTextView {

    public VehicleSpeedTextView(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {
            if(updatedValue != "") {
                textViewValue = "Current speed: ".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else if(updatedValue instanceof Double) {
            double d = (Double) updatedValue;

            if(d >= 0.0) {
                textViewValue = String.format("Current speed: %3.1f", d);

            } else {
                textViewValue = "";
            }

        } else {
            if(updatedValue != null) {
                textViewValue = "Current speed: ".concat(updatedValue.toString());

            } else {
                textViewValue = "";
            }
        }

        this.textView.setText(textViewValue);

    }
}
