package com.yaroslavm87.testtask01.View;

import android.widget.TextView;

import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Vehicle.States.VehicleState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnEventTextViewColorChanger implements Subscriber {

    private List<TextView> textViewList;
    private int colorRaceState;
    private int colorGotPunctureState;
    private int colorFinishedState;

    {
        colorRaceState = R.color.textViewColorVehicleRaceState;
        colorGotPunctureState = R.color.textViewColorVehicleGotPunctureState;
        colorFinishedState = R.color.textViewColorVehicleFinishedState;
    }

    public OnEventTextViewColorChanger() {
        this.textViewList = new ArrayList<>(4);
    }

    @Override
    public void receiveUpdate(Object value) {

        if (value instanceof VehicleState) {

            VehicleState state = (VehicleState) value;

            switch (state.getType()) {

                case RACE:
                    setColorToTextViews(colorRaceState);
                    break;

                case GOT_PUNCTURE:
                    setColorToTextViews(colorGotPunctureState);
                    break;

                case FINISHED:
                    setColorToTextViews(colorFinishedState);
                    break;
            }
        }

    }

    public void addTextViewToList(TextView ...textView) {
        this.textViewList.addAll(Arrays.asList(textView));
    }

    private void setColorToTextViews(int color) {
        for(TextView tv : this.textViewList) {
            tv.setBackgroundResource(color);
        }
    }
}
