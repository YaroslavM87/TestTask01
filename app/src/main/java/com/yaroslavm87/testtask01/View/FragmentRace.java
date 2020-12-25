package com.yaroslavm87.testtask01.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.R;
import com.yaroslavm87.testtask01.Singletone;

public class FragmentRace extends Fragment {

    private ActivityTextView trackLength;
    private ActivityTextView raceTimer;

//    ActivityTextView vehicleType;
//    ActivityTextView vehicleCurrentSpeedAndState;
//    ActivityTextView vehicleDistanceTravelled;


    RecyclerView recyclerViewVehicleRaceList;
    AdapterForRecyclerViewRaceList adapterForRecyclerViewRaceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeTextViews();
        initializeRecyclerViewForVehicleRaceList();
        subscribeObjectsForEvents();
    }

    private void initializeTextViews() {
        this.trackLength = new TextViewTrackLength(
                (TextView) requireView().findViewById(R.id.textViewTrackLength)
        );
        this.trackLength.receiveUpdate(Singletone.raceManager.getTrackLength());

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
                Singletone.raceManager.getPublisher();

//        Publisher vehicleBufferPublisher =
//                this.initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getPublisher();
//
//        Publisher vehicleStartListPublisher =
//                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getPublisher();



        vehicleRaceManagerPublisher.subscribeForEvent(
                this.raceTimer,
                EventType.RACE_MANAGER_VALUE_CHANGED_RACE_TIMER
        );

//        vehicleBufferPublisher.subscribeForEvent(
//                this.vehicleBufferVehicleType,
//                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
//                EventType.VEHICLE_BUFFER_VEHICLE_DELETED
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                this.vehicleBufferVehicleType,
//                EventType.VEHICLE_TYPE_CHANGED
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                this.vehicleBufferVehicleSpeed,
//                EventType.VEHICLE_VALUE_CHANGED_MAX_SPEED
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                this.vehicleBufferVehiclePunctureProbability,
//                EventType.VEHICLE_VALUE_CHANGED_PUNCTURE_PROBABILITY
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                this.vehicleBufferVehicleAdditionalValue,
//                EventType.VEHICLE_VALUE_CHANGED_ADDITIONAL_VALUE
//        );
//
//        vehicleStartListPublisher.subscribeForEvent(
//                this.adapterNotifier,
//                EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED,
//                EventType.VEHICLE_START_LIST_VEHICLE_DELETED
//        );
    }
//
//    private void defineActionWhenClickVehicleTypesAvailableListItem(
//            AdapterForRecyclerViewVehicleTypes adapter
//    ) {
//        adapter.setOnEntryClickListener(
//                position -> new AddNewVehicleControllerCommand(
//                        initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes().get(position),
//                        initializeObjectsControllerCommand.getRaceManager()
//                ).execute());
//    }
//
//    private void defineActionWhenClickButtonReduceVehicleFromBufferValue() {
//        this.reduceVehicleFromBufferValue.clickButton(
//                initializeObjectsControllerCommand.
//                        getRaceManager().
//                        getVehicleBuffer().
//                        getVehicleFromBuffer(),
//                -10);
//    }
//
//    private void defineActionWhenClickButtonIncreaseVehicleFromBufferValue() {
//        this.increaseVehicleFromBufferValue.clickButton(
//                initializeObjectsControllerCommand.
//                        getRaceManager().
//                        getVehicleBuffer().
//                        getVehicleFromBuffer(),
//                10);
//    }
//
//    private void addNewVehicle() {
//        new AddVehicleToStartListControllerCommand(
//                this.initializeObjectsControllerCommand.getRaceManager()
//        ).execute();
//    }
//
//    private void reduceTrackLength() {
//        new SetTrackLengthControllerCommand(
//                this.initializeObjectsControllerCommand.getRaceManager(),
//                -100
//        ).execute();
//    }
//
//    private void increaseTrackLength() {
//        new SetTrackLengthControllerCommand(
//                this.initializeObjectsControllerCommand.getRaceManager(),
//                100
//        ).execute();
//    }
}