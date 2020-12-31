package com.yaroslavm87.testtask01.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;
import com.yaroslavm87.testtask01.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnVehicleBufferChangedActivityTextViewDataProvider implements Subscriber {

    private ActivityTextView vehicleType;
    private ActivityTextView vehicleSpeed;
    private ActivityTextView vehiclePunctureProbability;
    private ActivityTextView vehicleAdditionalValue;
    private List<TextView> textViewList;
    private int colorMoto;
    private int drawMotoLT;
    private int drawMotoLB;
    private int drawMotoRT;
    private int drawMotoRB;
    private int colorCar;
    private int drawCarLT;
    private int drawCarLB;
    private int drawCarRT;
    private int drawCarRB;
    private int colorTruck;
    private int drawTruckLT;
    private int drawTruckLB;
    private int drawTruckRT;
    private int drawTruckRB;
    private int colorEmpty;
    private int drawEmptyLT;
    private int drawEmptyLB;
    private int drawEmptyRT;
    private int drawEmptyRB;

    {
        colorMoto = R.color.textViewColorMotoChosen;
        drawMotoLT = R.drawable.round_corner_left_side_top_moto;
        drawMotoLB = R.drawable.round_corner_left_side_bottom_moto;
        drawMotoRT = R.drawable.round_corner_right_side_top_moto;
        drawMotoRB = R.drawable.round_corner_right_side_bottom_moto;
        colorCar = R.color.textViewColorCarChosen;
        drawCarLT = R.drawable.round_corner_left_side_top_car;
        drawCarLB = R.drawable.round_corner_left_side_bottom_car;
        drawCarRT = R.drawable.round_corner_right_side_top_car;
        drawCarRB = R.drawable.round_corner_right_side_bottom_car;
        colorTruck = R.color.textViewColorTruckChosen;
        drawTruckLT = R.drawable.round_corner_left_side_top_truck;
        drawTruckLB = R.drawable.round_corner_left_side_bottom_truck;
        drawTruckRT = R.drawable.round_corner_right_side_top_truck;
        drawTruckRB = R.drawable.round_corner_right_side_bottom_truck;
        colorEmpty = R.color.colorBackgroundSecondary;
        drawEmptyLT = R.drawable.round_corner_left_side_top;
        drawEmptyLB = R.drawable.round_corner_left_side_bottom;
        drawEmptyRT = R.drawable.round_corner_right_side_top;
        drawEmptyRB = R.drawable.round_corner_right_side_bottom;
    }

    public OnVehicleBufferChangedActivityTextViewDataProvider(
            ActivityTextView vehicleType,
            ActivityTextView vehicleSpeed,
            ActivityTextView vehiclePunctureProbability,
            ActivityTextView vehicleAdditionalValue,
            TextView ...textViews
    ) {
        this.vehicleType = vehicleType;
        this.vehicleSpeed = vehicleSpeed;
        this.vehiclePunctureProbability = vehiclePunctureProbability;
        this.vehicleAdditionalValue = vehicleAdditionalValue;
        this.textViewList = new ArrayList<>(textViews.length);
        this.textViewList.addAll(Arrays.asList(textViews));
    }

    @Override
    public void receiveUpdate(Object value) {

        if (value instanceof Vehicle) {
            Vehicle vehicle = (Vehicle) value;
            setTextValue(vehicle);
            chooseBackgroundColor(vehicle.getVehicleType());

        } else if(value == null) {
            setEmptyValue();
            setEmptyColorToTextViews();
        }
    }

    private void setTextValue(Vehicle vehicle) {
        this.vehicleType.receiveUpdate(vehicle.getVehicleType());
        this.vehicleSpeed.receiveUpdate(vehicle.getMaxSpeed());
        this.vehiclePunctureProbability.receiveUpdate(vehicle.getPunctureProbability());
        //this.vehicleAdditionalValue.receiveUpdate(vehicle.);
    }

    private void setEmptyValue() {
        this.vehicleType.receiveUpdate(null);
        this.vehicleSpeed.receiveUpdate(null);
        this.vehiclePunctureProbability.receiveUpdate(null);
        //this.vehicleAdditionalValue.receiveUpdate(null);
    }

    private void chooseBackgroundColor(VehicleType vehicleType) {

        switch (vehicleType) {

            case TRUCK:
                setTruckColorToTextViews();
                break;

            case CAR:
                setCarColorToTextViews();
                break;

            case MOTORCYCLE:
                setMotoColorToTextViews();
                break;
        }
    }

    private void setMotoColorToTextViews() {
        this.vehicleType.getTextView().setBackgroundResource(this.drawMotoRT);
        this.vehicleSpeed.getTextView().setBackgroundResource(this.colorMoto);
        this.vehiclePunctureProbability.getTextView().setBackgroundResource(this.colorMoto);
        this.vehicleAdditionalValue.getTextView().setBackgroundResource(this.drawMotoRB);

        for(TextView tv : this.textViewList) {

            if(this.textViewList.indexOf(tv) == 0) {
                tv.setBackgroundResource(drawMotoLT);

            } else if(this.textViewList.indexOf(tv) == this.textViewList.size() - 1) {
                tv.setBackgroundResource(drawMotoLB);

            } else {
                tv.setBackgroundResource(this.colorMoto);
            }
        }
    }

    private void setCarColorToTextViews() {
        this.vehicleType.getTextView().setBackgroundResource(this.drawCarRT);
        this.vehicleSpeed.getTextView().setBackgroundResource(this.colorCar);
        this.vehiclePunctureProbability.getTextView().setBackgroundResource(this.colorCar);
        this.vehicleAdditionalValue.getTextView().setBackgroundResource(this.drawCarRB);

        for(TextView tv : this.textViewList) {

            if(this.textViewList.indexOf(tv) == 0) {
                tv.setBackgroundResource(drawCarLT);

            } else if(this.textViewList.indexOf(tv) == this.textViewList.size() - 1) {
                tv.setBackgroundResource(drawCarLB);

            } else {
                tv.setBackgroundResource(this.colorCar);
            }
        }
    }

    private void setTruckColorToTextViews() {
        this.vehicleType.getTextView().setBackgroundResource(this.drawTruckRT);
        this.vehicleSpeed.getTextView().setBackgroundResource(this.colorTruck);
        this.vehiclePunctureProbability.getTextView().setBackgroundResource(this.colorTruck);
        this.vehicleAdditionalValue.getTextView().setBackgroundResource(this.drawTruckRB);

        for(TextView tv : this.textViewList) {

            if(this.textViewList.indexOf(tv) == 0) {
                tv.setBackgroundResource(drawTruckLT);

            } else if(this.textViewList.indexOf(tv) == this.textViewList.size() - 1) {
                tv.setBackgroundResource(drawTruckLB);

            } else {
                tv.setBackgroundResource(this.colorTruck);
            }
        }
    }

    private void setEmptyColorToTextViews() {
        this.vehicleType.getTextView().setBackgroundResource(this.drawEmptyRT);
        this.vehicleSpeed.getTextView().setBackgroundResource(this.colorEmpty);
        this.vehiclePunctureProbability.getTextView().setBackgroundResource(this.colorEmpty);
        this.vehicleAdditionalValue.getTextView().setBackgroundResource(this.drawEmptyRB);

        for(TextView tv : this.textViewList) {

            if(this.textViewList.indexOf(tv) == 0) {
                tv.setBackgroundResource(drawEmptyLT);

            } else if(this.textViewList.indexOf(tv) == this.textViewList.size() - 1) {
                tv.setBackgroundResource(drawEmptyLB);

            } else {
                tv.setBackgroundResource(this.colorEmpty);
            }
        }
    }
}
