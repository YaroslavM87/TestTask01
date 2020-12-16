package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TextViewVehicleSpeed extends ActivityTextView implements View.OnClickListener {

    private ButtonConfig.TypeOfTextViewClicked typeOfTextViewClicked;
    private List<OnActivityTextViewClickListener> listenersList;

    public TextViewVehicleSpeed(TextView textView) {
        super(textView);
        this.typeOfTextViewClicked = ButtonConfig.TypeOfTextViewClicked.VEHICLE_MAX_SPEED;
        this.listenersList = new ArrayList<>(2);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {
            if(updatedValue != "") {
                textViewValue = "Max speed: ".concat((String)updatedValue);

            } else {
                textViewValue = "";
            }

        } else if(updatedValue instanceof Double) {
            double d = (Double) updatedValue;

            if(d >= 0.0) {
                textViewValue = String.format("Max speed:%n%3.1f", d);

            } else {
                textViewValue = "";
            }

        } else {
            if(updatedValue != null) {
                textViewValue = "Max speed: ".concat(updatedValue.toString());

            } else {
                textViewValue = "";
            }
        }

        this.textView.setText(textViewValue);
    }

    @Override
    public void onClick(View v) {
        for(OnActivityTextViewClickListener l : this.listenersList)
            l.onActivityTextViewClick(this.typeOfTextViewClicked);
    }

    public ButtonConfig.TypeOfTextViewClicked getTypeOfTextViewClicked() {
        return typeOfTextViewClicked;
    }

    public interface OnActivityTextViewClickListener {
        void onActivityTextViewClick(ButtonConfig.TypeOfTextViewClicked typeOfTextViewClicked);
    }

    public void setOnActivityTextViewClickListener(OnActivityTextViewClickListener listener) {
        this.listenersList.add(listener);
    }
}
