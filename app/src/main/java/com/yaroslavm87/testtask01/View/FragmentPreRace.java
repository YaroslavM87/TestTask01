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

public class FragmentPreRace extends Fragment implements Subscriber {

    ActivityTextView vehicleBufferVehicleType;
    TextViewVehicleSpeed vehicleBufferVehicleSpeed;
    ActivityTextView vehicleBufferVehiclePunctureProbability;
    ActivityTextView vehicleBufferVehicleAdditionalValue;
    ActivityTextView trackLength;

    Button addNewVehicle;
    ButtonVehicleValueConfig reduceVehicleFromBufferValue;
    ButtonVehicleValueConfig increaseVehicleFromBufferValue;
    Button reduceTrackLength;
    Button increaseTrackLength;
    Button startRace;

    RecyclerView recyclerViewVehicleTypes;
    AdapterForRecyclerViewVehicleTypes adapterForRecyclerViewVehicleTypes;

    RecyclerView recyclerViewVehicleStartList;
    AdapterForRecyclerViewStartList adapterForRecyclerViewStartList;
    AdapterNotifier adapterNotifier;

    int layoutForRecyclerView;

    InitializeObjectsControllerCommand initializeObjectsControllerCommand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pre_race_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeControllerElements();
        initializeButtons();
        initializeTextViews();
        initializeRecyclerViewForVehiclesAvailable();
        initializeRecyclerViewForVehicleStartList();
        subscribeObjectsForEvents();
    }

    private void initializeControllerElements() {
        this.initializeObjectsControllerCommand = new InitializeObjectsControllerCommand();
        this.initializeObjectsControllerCommand.execute();
    }

    private void initializeButtons() {
        this.addNewVehicle = requireView().findViewById(R.id.addNewVehicle);
        this.addNewVehicle.setOnClickListener(
                v -> addNewVehicle()
        );

        this.reduceVehicleFromBufferValue = new ButtonVehicleValueConfig(requireView().findViewById(R.id.vehicleValueReduce));
        this.reduceVehicleFromBufferValue.setOnClickListener(
                v -> defineActionWhenClickButtonReduceVehicleFromBufferValue()
        );

        this.increaseVehicleFromBufferValue = new ButtonVehicleValueConfig(requireView().findViewById(R.id.vehicleValueIncrease));
        this.increaseVehicleFromBufferValue.setOnClickListener(
                v -> defineActionWhenClickButtonIncreaseVehicleFromBufferValue()
        );

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
                        initializeObjectsControllerCommand.getRaceManager()
                ).execute()
        );
    }

    private void initializeTextViews() {
        this.vehicleBufferVehicleType = new TextViewVehicleType(
                (TextView) requireView().findViewById(R.id.vehicleBufferVehicleType)
        );

        this.vehicleBufferVehicleSpeed = new TextViewVehicleSpeed(
                (TextView) requireView().findViewById(R.id.vehicleBufferVehicleSpeed)
        );
        this.vehicleBufferVehicleSpeed.getTextView().setOnClickListener(this.vehicleBufferVehicleSpeed);
        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.increaseVehicleFromBufferValue);
        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.reduceVehicleFromBufferValue);

        this.vehicleBufferVehiclePunctureProbability = new TextViewVehiclePunctureProbability(
                (TextView) requireView().findViewById(R.id.vehicleBufferVehiclePunctureProb)
        );

        this.vehicleBufferVehicleAdditionalValue = new TextViewVehicleAdditionalValue(
                (TextView) requireView().findViewById(R.id.vehicleBufferVehicleAdditionalValue)
        );

        this.trackLength = new TextViewTrackLength(
                (TextView) requireView().findViewById(R.id.trackLength)
        );
        this.trackLength.receiveUpdate(
                this.initializeObjectsControllerCommand.getRaceManager().getTrackLength()
        );
    }

    private void initializeRecyclerViewForVehiclesAvailable() {

        int vehicleType = R.id.typeOfVehicleAvailable;
        int empty = R.id.emptyTextView;

        this.recyclerViewVehicleTypes = requireView().findViewById(R.id.listOfVehiclesAvailable);
        this.layoutForRecyclerView = R.layout.recycler_view_for_list_of_vehicles_available;

        this.adapterForRecyclerViewVehicleTypes = new AdapterForRecyclerViewVehicleTypes(
                this.initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes(),
                this.layoutForRecyclerView,
                vehicleType,
                empty
        );

        LinearLayoutManager layoutManagerForRecyclerView = new LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
        );

        this.recyclerViewVehicleTypes.hasFixedSize();
        this.recyclerViewVehicleTypes.setAdapter(this.adapterForRecyclerViewVehicleTypes);
        this.recyclerViewVehicleTypes.setLayoutManager(layoutManagerForRecyclerView);

        defineActionWhenClickVehicleTypesAvailableListItem(adapterForRecyclerViewVehicleTypes);
    }

    private void initializeRecyclerViewForVehicleStartList() {

        int vehicleType = R.id.vehicleRaceListTextViewType;
        int vehicleSpeed = R.id.vehicleRaceListTextViewVehicleCurrentSpeedAndState;

        this.recyclerViewVehicleStartList = requireView().findViewById(R.id.vehicleStartList);
        this.layoutForRecyclerView = R.layout.recycler_view_for_vehicle_start_list;

        this.adapterForRecyclerViewStartList = new AdapterForRecyclerViewStartList(
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getList(),
                this.layoutForRecyclerView,
                vehicleType,
                vehicleSpeed
        );

        LinearLayoutManager layoutManagerForRecyclerView = new LinearLayoutManager(requireActivity());

        this.adapterNotifier = new AdapterNotifier(adapterForRecyclerViewStartList);

        this.recyclerViewVehicleStartList.setAdapter(adapterForRecyclerViewStartList);
        this.recyclerViewVehicleStartList.setLayoutManager(layoutManagerForRecyclerView);
    }

    private void subscribeObjectsForEvents() {

        Publisher vehicleRaceManagerPublisher =
               this.initializeObjectsControllerCommand.getRaceManager().getPublisher();

        Publisher vehicleBufferPublisher =
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getPublisher();

        Publisher vehicleStartListPublisher =
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getPublisher();

        vehicleRaceManagerPublisher.subscribeForEvent(
                this.trackLength,
                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
        );

        vehicleRaceManagerPublisher.subscribeForEvent(
                this,
                EventType.RACE_STARTED
        );

        vehicleBufferPublisher.subscribeForEvent(
                this.vehicleBufferVehicleType,
                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
                EventType.VEHICLE_BUFFER_VEHICLE_DELETED
//                this.vehicleBufferVehicleSpeed,
//                this.vehicleBufferVehiclePunctureProbability,
//                this.vehicleBufferVehicleAdditionalValue
        );

        vehicleBufferPublisher.subscribeForEvent(
                this.vehicleBufferVehicleType,
                EventType.VEHICLE_TYPE_CHANGED
        );

        vehicleBufferPublisher.subscribeForEvent(
                this.vehicleBufferVehicleSpeed,
                EventType.VEHICLE_VALUE_CHANGED_MAX_SPEED
        );

        vehicleBufferPublisher.subscribeForEvent(
                this.vehicleBufferVehiclePunctureProbability,
                EventType.VEHICLE_VALUE_CHANGED_PUNCTURE_PROBABILITY
        );

        vehicleBufferPublisher.subscribeForEvent(
                this.vehicleBufferVehicleAdditionalValue,
                EventType.VEHICLE_VALUE_CHANGED_ADDITIONAL_VALUE
        );

        vehicleStartListPublisher.subscribeForEvent(
                this.adapterNotifier,
                EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED,
                EventType.VEHICLE_START_LIST_VEHICLE_DELETED
        );
    }

    private void defineActionWhenClickVehicleTypesAvailableListItem(
            AdapterForRecyclerViewVehicleTypes adapter
    ) {
        adapter.setOnEntryClickListener(
                position -> new AddNewVehicleControllerCommand(
                        initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes().get(position),
                        initializeObjectsControllerCommand.getRaceManager()
                ).execute());
    }

    private void defineActionWhenClickButtonReduceVehicleFromBufferValue() {
        this.reduceVehicleFromBufferValue.clickButton(
                initializeObjectsControllerCommand.
                        getRaceManager().
                        getVehicleBuffer().
                        getVehicleFromBuffer(),
                -10);
    }

    private void defineActionWhenClickButtonIncreaseVehicleFromBufferValue() {
        this.increaseVehicleFromBufferValue.clickButton(
                initializeObjectsControllerCommand.
                        getRaceManager().
                        getVehicleBuffer().
                        getVehicleFromBuffer(),
                10);
    }

    private void addNewVehicle() {
        new AddVehicleToStartListControllerCommand(
                this.initializeObjectsControllerCommand.getRaceManager()
        ).execute();
    }

    private void reduceTrackLength() {
        new SetTrackLengthControllerCommand(
                this.initializeObjectsControllerCommand.getRaceManager(),
                -100
        ).execute();
    }

    private void increaseTrackLength() {
        new SetTrackLengthControllerCommand(
                this.initializeObjectsControllerCommand.getRaceManager(),
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