package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TextViewTrackLength extends ActivityTextView {

    public TextViewTrackLength(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {

            if(updatedValue != "") {
                textViewValue = "".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else if(updatedValue instanceof Double) {
            double d = (Double) updatedValue;

            if(d >= 0.0) {
                textViewValue = String.format("%1.0f", d);

            } else {
                textViewValue = "";
            }

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
