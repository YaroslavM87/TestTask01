package com.yaroslavm87.testtask01.View;

import android.widget.TextView;

public class TextViewVehicleType extends ActivityTextView {

    public TextViewVehicleType(TextView textView) {
        super(textView);
    }

    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {

            if(updatedValue != "") {
                textViewValue = "".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else {
            if(updatedValue != null) {
                textViewValue = "".concat(updatedValue.toString());

            }  else {
                textViewValue = "";
            }
        }

        super.textView.setText(textViewValue);
    }
}