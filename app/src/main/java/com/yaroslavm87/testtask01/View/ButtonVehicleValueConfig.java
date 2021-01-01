package com.yaroslavm87.testtask01.View;

import android.view.View;
import android.widget.Button;

import com.yaroslavm87.testtask01.Controller.SetVehicleMaxSpeedControllerCommand;
import com.yaroslavm87.testtask01.Vehicle.Vehicle;

public class ButtonVehicleValueConfig implements TextViewVehicleSpeed.OnActivityTextViewClickListener {

    private Button button;
    private ButtonVehicleValueConfig.TypeOfTextViewClicked typeOfTextViewClicked;

    public ButtonVehicleValueConfig(Button button) {
        this.button = button;
    }

    public void clickButton(Vehicle vehicle, double changeForVehicleValue) {
        if(this.typeOfTextViewClicked != null & vehicle != null) {
            chooseAppropriateCommand(vehicle, changeForVehicleValue, this.typeOfTextViewClicked);
        }
    }

    @Override
    public void onActivityTextViewClick(TypeOfTextViewClicked typeOfTextViewClicked) {
        this.typeOfTextViewClicked = typeOfTextViewClicked;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.button.setOnClickListener(onClickListener);
    }

    private void chooseAppropriateCommand(
            Vehicle vehicle,
            double changeForVehicleValue,
            ButtonVehicleValueConfig.TypeOfTextViewClicked type
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

    public enum TypeOfTextViewClicked {
        VEHICLE_MAX_SPEED,
        VEHICLE_PUNCTURE_PROBABILITY,
        VEHICLE_ADDITIONAL_VALUE
    }
}


