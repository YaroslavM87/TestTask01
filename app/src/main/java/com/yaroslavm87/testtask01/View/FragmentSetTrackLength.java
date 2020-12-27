package com.yaroslavm87.testtask01.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.Controller.AddNewVehicleControllerCommand;
import com.yaroslavm87.testtask01.Controller.AddVehicleToStartListControllerCommand;
import com.yaroslavm87.testtask01.Controller.InitializeObjectsControllerCommand;
import com.yaroslavm87.testtask01.Controller.SetTrackLengthControllerCommand;
import com.yaroslavm87.testtask01.Controller.StartRaceControllerCommand;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.Notifications.Subscriber;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Singletone;

public class FragmentSetTrackLength extends Fragment implements Subscriber {

    ActivityTextView trackLength;

    Button reduceTrackLength;
    Button increaseTrackLength;
    Button startRace;
    RaceManager raceManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_track_length_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.raceManager = Singletone.raceManager;
        initializeButtons();
        initializeTextViews();
        subscribeObjectsForEvents();
    }

    private void initializeButtons() {
        this.reduceTrackLength = requireView().findViewById(R.id.trackLengthReduce);
        this.reduceTrackLength.setOnClickListener(
                v -> reduceTrackLength()
        );

        this.increaseTrackLength = requireView().findViewById(R.id.trackLengthIncrease);
        this.increaseTrackLength.setOnClickListener(
                v -> increaseTrackLength()
        );

        this.startRace = requireView().findViewById(R.id.startRace);
        this.startRace.setOnClickListener(
                v -> new StartRaceControllerCommand(
                        raceManager
                ).execute()
        );
    }

    private void initializeTextViews() {

        this.trackLength = new TextViewTrackLength(
                (TextView) requireView().findViewById(R.id.trackLength)
        );
        this.trackLength.receiveUpdate(
                raceManager.getTrackLength()
        );
    }

    private void subscribeObjectsForEvents() {

        Publisher vehicleRaceManagerPublisher =
                raceManager.getPublisher();

        vehicleRaceManagerPublisher.subscribeForEvent(
                this.trackLength,
                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
        );

        vehicleRaceManagerPublisher.subscribeForEvent(
                this,
                EventType.RACE_STARTED
        );
    }

    private void reduceTrackLength() {
        new SetTrackLengthControllerCommand(
                raceManager,
                -100
        ).execute();
    }

    private void increaseTrackLength() {
        new SetTrackLengthControllerCommand(
                raceManager,
                100
        ).execute();
    }

    public void replaceFragment() {
        getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment, FragmentRace.class, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void receiveUpdate(Object updatedValue) {
        replaceFragment();
    }
}