package com.yaroslavm87.testtask01.View;

import android.view.View;
import android.widget.Button;

import androidx.annotation.ColorInt;

import com.yaroslavm87.testtask01.Controller.SetVehicleMaxSpeedControllerCommand;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class ButtonConfig implements TextViewVehicleSpeed.OnActivityTextViewClickListener {

    private Button button;
    private ButtonConfig.TypeOfTextViewClicked typeOfTextViewClicked;

    public ButtonConfig(Button button) {
        this.button = button;
    }

    public void clickButton(Vehicle vehicle, double changeForVehicleValue) {
        if(this.typeOfTextViewClicked != null) {
            chooseAppropriateCommand(vehicle, changeForVehicleValue, this.typeOfTextViewClicked);
        }
    }

    private void chooseAppropriateCommand(
            Vehicle vehicle,
            double changeForVehicleValue,
            ButtonConfig.TypeOfTextViewClicked type
    ) {

        switch(type) {

            case VEHICLE_MAX_SPEED:
                new SetVehicleMaxSpeedControllerCommand(vehicle, changeForVehicleValue).execute();
                break;
//
//                case VEHICLE_PUNCTURE_PROBABILITY:
//                break;
//
//                case VEHICLE_ADDITIONAL_VALUE:
//                break;
        }

    }

    @Override
    public void onActivityTextViewClick(TypeOfTextViewClicked typeOfTextViewClicked) {
        this.typeOfTextViewClicked = typeOfTextViewClicked;
    }


    public enum TypeOfTextViewClicked {
        VEHICLE_MAX_SPEED,
        VEHICLE_PUNCTURE_PROBABILITY,
        VEHICLE_ADDITIONAL_VALUE
    }
}


