package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class VehiclePunctureProbabilityTextView extends ActivityTextView {

    public VehiclePunctureProbabilityTextView(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {
            if(updatedValue != "") {
                textViewValue = "Puncture probability: ".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else if (updatedValue instanceof Double) {
            double d = (Double) updatedValue;
            if(d >= 0.0) {
                textViewValue = String.format("Puncture probability: %1.2f", d);

            } else {
                textViewValue = "";
            }

        } else {
            if(updatedValue != null) {
                textViewValue = "Puncture probability: ".concat(updatedValue.toString());

            } else {
                textViewValue = "";
            }
        }

        this.textView.setText(textViewValue);
    }
}