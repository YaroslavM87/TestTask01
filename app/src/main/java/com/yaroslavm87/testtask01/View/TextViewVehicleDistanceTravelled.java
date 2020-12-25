package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TextViewVehicleDistanceTravelled extends ActivityTextView {

    public TextViewVehicleDistanceTravelled(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {
            textViewValue = "".concat((String)updatedValue);

        } else if(updatedValue instanceof Double) {
            double d = (Double) updatedValue;
            textViewValue = String.format("%4.2f", d);

        } else {
            if(updatedValue != null) {
                textViewValue = "".concat(updatedValue.toString());

            } else {
                textViewValue = "";
            }
        }

        this.textView.setText(textViewValue);
    }
}
