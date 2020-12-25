package com.yaroslavm87.testtask01.View;

import android.view.View;
import android.widget.TextView;

public class TextViewVehicleAdditionalValue extends ActivityTextView {

    public TextViewVehicleAdditionalValue(TextView textView) {
        super(textView);
    }

    @Override
    public void receiveUpdate(Object updatedValue) {
//        String textViewValue;
//        if (updatedValue instanceof String) {
//            textViewValue = (String)updatedValue;
//
//        } else if(updatedValue instanceof Double) {
//            double d = (Double) updatedValue;
//            textViewValue = String.format("%4.2f", d);
//
//        } else {
//            textViewValue = updatedValue.toString();
//        }
//
//        this.textView.setText(textViewValue);
    }
}
