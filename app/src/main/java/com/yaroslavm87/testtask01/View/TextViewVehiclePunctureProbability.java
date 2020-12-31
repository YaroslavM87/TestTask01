package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TextViewVehiclePunctureProbability extends ActivityTextView {

    public TextViewVehiclePunctureProbability(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue = "";

        if (updatedValue instanceof String) {
            if(updatedValue != "") {
                textViewValue = "".concat((String)updatedValue);

            }

        } else if (updatedValue instanceof Double) {
            double d = (Double) updatedValue;
            if(d >= 0.0) {
                textViewValue = String.format("%1.2f", d);

            }

        } else {
            if(updatedValue != null) {
                textViewValue = "".concat(updatedValue.toString());

            }
        }

        this.textView.setText(textViewValue);
    }
}