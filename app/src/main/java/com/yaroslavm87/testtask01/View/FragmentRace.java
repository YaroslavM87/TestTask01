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

import com.yaroslavm87.testtask01.Controller.SetTrackLengthControllerCommand;
import com.yaroslavm87.testtask01.Controller.StartRaceControllerCommand;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.RaceManager.RaceManager;
import com.yaroslavm87.testtask01.Singletone;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentRace extends Fragment {

    private ActivityTextView trackLength;
    private ActivityTextView raceTimer;

    private Button reduceTrackLength;
    private Button increaseTrackLength;
    private Button startRace;
    private RaceManager raceManager;

    private RecyclerView recyclerViewVehicleRaceList;
    private AdapterForRecyclerViewRaceList adapterForRecyclerViewRaceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.raceManager = Singletone.raceManager;
        initializeButtons();
        initializeTextViews();
        initializeRecyclerViewForVehicleRaceList();
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
                v -> startRace()
        );
    }

    private void initializeTextViews() {

        this.trackLength = new TextViewTrackLength(
                (TextView) requireView().findViewById(R.id.trackLength)
        );
        this.trackLength.receiveUpdate(
                raceManager.getTrackLength()
        );

        this.raceTimer = new TextViewRaceTimer(
                (TextView) requireView().findViewById(R.id.textViewRaceTimer)
        );
    }

    private void initializeRecyclerViewForVehicleRaceList() {

        int vehicleType = R.id.vehicleRaceListTextViewType;
        int vehicleCurrentSpeedAndStatus = R.id.vehicleRaceListTextViewVehicleCurrentSpeedAndState;
        int vehicleDistanceTravelled = R.id.vehicleRaceListTextViewVehicleDistanceTravelled;
        int layoutForRecyclerView;
        int vehicleFinishTime = R.id.vehicleRaceListTextViewVehicleFinishTime;

        this.recyclerViewVehicleRaceList = requireView().findViewById(R.id.vehicleRaceList);
        layoutForRecyclerView = R.layout.recycler_view_for_vehicle_race_list;

        this.adapterForRecyclerViewRaceList = new AdapterForRecyclerViewRaceList(
                Singletone.raceManager.getVehicleStartList().getList(),
                layoutForRecyclerView,
                vehicleType,
                vehicleCurrentSpeedAndStatus,
                vehicleDistanceTravelled,
                vehicleFinishTime
        );

        LinearLayoutManager layoutManagerForRecyclerView = new LinearLayoutManager(requireActivity());

        this.recyclerViewVehicleRaceList.setAdapter(adapterForRecyclerViewRaceList);
        this.recyclerViewVehicleRaceList.setLayoutManager(layoutManagerForRecyclerView);
    }

    private void subscribeObjectsForEvents() {

        Publisher vehicleRaceManagerPublisher =
                raceManager.getPublisher();

        vehicleRaceManagerPublisher.subscribeForEvent(
                this.trackLength,
                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
        );

        vehicleRaceManagerPublisher.subscribeForEvent(
                this.raceTimer,
                EventType.RACE_MANAGER_VALUE_CHANGED_RACE_TIMER
        );

//        vehicleRaceManagerPublisher.subscribeForEvent(
//                this,
//                EventType.RACE_STARTED
//        );
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

    private void startRace() {

//        TimerTask timerTask = new TimerTask(){
//            @Override
//            public void run() {
//                new StartRaceControllerCommand(Singletone.raceManager).execute();
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 5000);

        new StartRaceControllerCommand(Singletone.raceManager).execute();
    }


}