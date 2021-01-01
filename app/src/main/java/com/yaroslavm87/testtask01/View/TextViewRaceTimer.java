package com.yaroslavm87.testtask01.View;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TextViewRaceTimer extends ActivityTextView {

    public TextViewRaceTimer(TextView textView) {
        super(textView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void receiveUpdate(Object updatedValue) {

        String textViewValue;

        if (updatedValue instanceof String) {
            textViewValue = "".concat((String)updatedValue);

        } else if(updatedValue instanceof Long) {
            long timerValue = (Long) updatedValue;
            textViewValue = timeValueToFormattedRepresentation(timerValue);

        } else {
            if(updatedValue != null) {
                textViewValue = "".concat(updatedValue.toString());

            } else {
                textViewValue = "";
            }
        }

        super.textView.setText(textViewValue);
    }

    private String timeValueToFormattedRepresentation(long timeValue) {
        return String.format("%s : %s" ,
                parseLongToStringAddZero(convertMillToMin(timeValue)),
                parseLongToStringAddZero(convertMillToSec(timeValue)));
    }

    private long convertMillToMin(long timeValue) {
        return ((timeValue / 1000) - convertMillToSec(timeValue)) / 60;
    }

    private long convertMillToSec(long timeValue) {
        return (timeValue / 1000) % 60;
    }

    private String parseLongToStringAddZero(long timeValue) {
        return timeValue < 10 ? ("0" + timeValue) : Long.toString(timeValue);
    }
}
