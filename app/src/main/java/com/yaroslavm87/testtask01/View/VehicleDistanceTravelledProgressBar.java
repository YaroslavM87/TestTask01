package com.yaroslavm87.testtask01.View;

import android.widget.ProgressBar;
import com.yaroslavm87.testtask01.Notifications.Subscriber;

public class VehicleDistanceTravelledProgressBar {


    class MaxProgressValue implements Subscriber {

        private int maxProgressValue;

        MaxProgressValue() {}

        @Override
        public void receiveUpdate(Object updatedValue) {

            if (updatedValue instanceof String) {
                if (updatedValue != "") {
                    this.maxProgressValue = Integer.parseInt((String) updatedValue);
                }

            } else if (updatedValue instanceof Double) {
                double i = (Double) updatedValue;
                this.maxProgressValue = (int) Math.max(i, 0);

            } else if (updatedValue instanceof Integer) {
                int d = (Integer) updatedValue;
                this.maxProgressValue = Math.max(d, 0);

            } else {
                this.maxProgressValue = 0;
            }

            progressBar.setMax(this.maxProgressValue);
        }
    }


    class CurrentProgressValue implements Subscriber {

        private int currentProgressValue;

        CurrentProgressValue() {}

        @Override
        public void receiveUpdate(Object updatedValue) {

            if (updatedValue instanceof String) {
                if (updatedValue != "") {
                    currentProgressValue = Integer.parseInt((String) updatedValue);
                }

            } else if (updatedValue instanceof Double) {
                double i = (Double) updatedValue;
                currentProgressValue = (int) Math.max(i, 0);

            } else if (updatedValue instanceof Integer) {
                int d = (Integer) updatedValue;
                currentProgressValue = Math.max(d, 0);

            } else {
                currentProgressValue = 0;
            }

            progressBar.setProgress(currentProgressValue);
        }
    }


    private ProgressBar progressBar;
    private CurrentProgressValue currentProgressValue;
    private MaxProgressValue maxProgressValue;

    public VehicleDistanceTravelledProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.currentProgressValue = new CurrentProgressValue();
        this.maxProgressValue = new MaxProgressValue();
    }

    public CurrentProgressValue getCurrentProgressValue() {
        return currentProgressValue;
    }

    public MaxProgressValue getMaxProgressValue() {
        return maxProgressValue;
    }
}