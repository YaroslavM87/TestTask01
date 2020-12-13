package com.yaroslavm87.testtask01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yaroslavm87.testtask01.Controller.AddNewVehicleControllerCommand;
import com.yaroslavm87.testtask01.Controller.AddVehicleToStartListControllerCommand;
import com.yaroslavm87.testtask01.Controller.InitializeObjectsControllerCommand;
import com.yaroslavm87.testtask01.Controller.SetTrackLengthControllerCommand;
import com.yaroslavm87.testtask01.Controller.StartRaceControllerCommand;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.View.ActivityTextView;
import com.yaroslavm87.testtask01.View.AdapterForRecyclerViewVehicleTypes;
import com.yaroslavm87.testtask01.View.AdapterNotifier;
import com.yaroslavm87.testtask01.View.AdapterForRecyclerViewStartList;
import com.yaroslavm87.testtask01.View.TrackLengthTextView;
import com.yaroslavm87.testtask01.View.VehicleAdditionalValueTextView;
import com.yaroslavm87.testtask01.View.VehiclePunctureProbabilityTextView;
import com.yaroslavm87.testtask01.View.VehicleSpeedTextView;
import com.yaroslavm87.testtask01.View.VehicleTypeTextView;

public class MainActivity extends AppCompatActivity {

    ActivityTextView vehicleBufferVehicleType;
    ActivityTextView vehicleBufferVehicleSpeed;
    ActivityTextView vehicleBufferVehiclePunctureProbability;
    ActivityTextView vehicleBufferVehicleAdditionalValue;
    ActivityTextView trackLength;
    Button addNewVehicle;
    Button reduceTrackLength;
    Button increaseTrackLength;
    RecyclerView recyclerViewVehicleTypes;
    RecyclerView recyclerViewVehicleStartList;
    AdapterForRecyclerViewVehicleTypes adapterForRecyclerViewVehicleTypes;
    AdapterForRecyclerViewStartList adapterForRecyclerViewStartList;
    LinearLayoutManager layoutManagerForRecyclerView;
    int layoutForRecyclerView;

    InitializeObjectsControllerCommand initializeObjectsControllerCommand;
    AdapterNotifier adapterNotifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeControllerElements();
        initializeViewGroupElements();
        subscribeObjectsForEvents();

    }

    private void initializeControllerElements() {
        initializeObjectsControllerCommand = new InitializeObjectsControllerCommand();
        initializeObjectsControllerCommand.execute();
    }

    private void initializeViewGroupElements() {
        initializeButtons();
        initializeTextViews();
        initializeRecyclerViewForVehiclesAvailable();
        initializeRecyclerViewForVehicleStartList();
    }

    private void initializeButtons() {
        addNewVehicle = findViewById(R.id.addNewVehicle);
        reduceTrackLength = findViewById(R.id.trackLengthReduce);
        increaseTrackLength = findViewById(R.id.trackLengthIncrease);
    }

    private void initializeTextViews() {
        vehicleBufferVehicleType = new VehicleTypeTextView(
                (TextView) findViewById(R.id.vehicleBufferVehicleType)
        );

        vehicleBufferVehicleSpeed = new VehicleSpeedTextView(
                (TextView) findViewById(R.id.vehicleBufferVehicleSpeed)
        );
        vehicleBufferVehiclePunctureProbability = new VehiclePunctureProbabilityTextView(
                (TextView) findViewById(R.id.vehicleBufferVehiclePunctureProb)
        );
        vehicleBufferVehicleAdditionalValue = new VehicleAdditionalValueTextView(
                (TextView) findViewById(R.id.vehicleBufferVehicleAdditionalValue)
        );
        trackLength = new TrackLengthTextView(
                (TextView) findViewById(R.id.trackLength)
        );
    }

    private void initializeRecyclerViewForVehiclesAvailable() {

        int vehicleType = R.id.typeOfVehicleAvailable;

        recyclerViewVehicleTypes = findViewById(R.id.listOfVehiclesAvailable);
        layoutForRecyclerView = R.layout.recycler_view_for_list_of_vehicles_available;

        adapterForRecyclerViewVehicleTypes = new AdapterForRecyclerViewVehicleTypes(
                this.initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes(),
                this.layoutForRecyclerView,
               vehicleType
        );

        layoutManagerForRecyclerView = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );

        recyclerViewVehicleTypes.hasFixedSize();
        recyclerViewVehicleTypes.setAdapter(adapterForRecyclerViewVehicleTypes);
        recyclerViewVehicleTypes.setLayoutManager(layoutManagerForRecyclerView);

        defineActionWhenClickVehicleTypesAvailableListItem(adapterForRecyclerViewVehicleTypes);
    }

    private void initializeRecyclerViewForVehicleStartList() {

        int vehicleType = R.id.vehicleStartListTextViewType;
        int vehicleSpeed = R.id.vehicleStartListTextViewVehicleSpeed;
        int vehicleDistanceTravelled = R.id.vehicleStartListTextViewVehicleDistanceTravelled;

        recyclerViewVehicleStartList = findViewById(R.id.listOfVehicleStartList);
        layoutForRecyclerView = R.layout.recycler_view_for_vehicle_start_list;


        adapterForRecyclerViewStartList = new AdapterForRecyclerViewStartList(
                initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getList(),
                layoutForRecyclerView,
                vehicleType,
                vehicleSpeed,
                vehicleDistanceTravelled,
                initializeObjectsControllerCommand.getRaceManager().getPublisher()
        );

        layoutManagerForRecyclerView = new LinearLayoutManager(this);

        adapterNotifier = new AdapterNotifier(adapterForRecyclerViewStartList);

        recyclerViewVehicleStartList.setAdapter(adapterForRecyclerViewStartList);
        recyclerViewVehicleStartList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void subscribeObjectsForEvents() {

        Publisher vehicleRaceManagerPublisher =
                initializeObjectsControllerCommand.getRaceManager().getPublisher();

        Publisher vehicleBufferPublisher =
                initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getPublisher();

        Publisher vehicleStartListPublisher =
                initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getPublisher();

        vehicleRaceManagerPublisher.subscribeForEvent(
                trackLength,
                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
        );

        vehicleBufferPublisher.subscribeForEvent(
                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
                vehicleBufferVehicleType,
                vehicleBufferVehicleSpeed,
                vehicleBufferVehiclePunctureProbability,
                vehicleBufferVehicleAdditionalValue
        );

        vehicleBufferPublisher.subscribeForEvent(
                EventType.VEHICLE_BUFFER_VEHICLE_DELETED,
                vehicleBufferVehicleType,
                vehicleBufferVehicleSpeed,
                vehicleBufferVehiclePunctureProbability,
                vehicleBufferVehicleAdditionalValue
        );

        vehicleBufferPublisher.subscribeForEvent(
                vehicleBufferVehicleType,
                EventType.VEHICLE_TYPE_CHANGED
        );

        vehicleBufferPublisher.subscribeForEvent(
                vehicleBufferVehicleSpeed,
                EventType.VEHICLE_VALUE_CHANGED_SPEED
        );


        vehicleBufferPublisher.subscribeForEvent(
                vehicleBufferVehiclePunctureProbability,
                EventType.VEHICLE_VALUE_CHANGED_PUNCTURE_PROBABILITY
        );


        vehicleBufferPublisher.subscribeForEvent(
                vehicleBufferVehicleAdditionalValue,
                EventType.VEHICLE_VALUE_CHANGED_ADDITIONAL_VALUE
        );

        vehicleStartListPublisher.subscribeForEvent(
                adapterNotifier,
                EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED,
                EventType.VEHICLE_START_LIST_VEHICLE_DELETED
        );
    }

    public void addNewVehicle(View addNewVehicle) {
        new AddVehicleToStartListControllerCommand(
                initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer(),
                initializeObjectsControllerCommand.getRaceManager().getVehicleStartList()
        ).execute();
    }

    public void reduceTrackLength(View addNewVehicle) {
        new SetTrackLengthControllerCommand(initializeObjectsControllerCommand.getRaceManager(), -100).execute();
    }

    public void increaseTrackLength(View addNewVehicle) {
        new SetTrackLengthControllerCommand(initializeObjectsControllerCommand.getRaceManager(), 100).execute();
    }

    public void startRace(View addNewVehicle) {
        new StartRaceControllerCommand(initializeObjectsControllerCommand.getRaceManager()).execute();
    }

    public void defineActionWhenClickVehicleTypesAvailableListItem(
            AdapterForRecyclerViewVehicleTypes adapter
    ) {
        adapter.setOnEntryClickListener(
                new AdapterForRecyclerViewVehicleTypes.OnEntryClickListener() {
                @Override
                public void onEntryClick(View view, int position) {
                    new AddNewVehicleControllerCommand(
                            initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes().get(position),
                            initializeObjectsControllerCommand.getRaceManager()
                    ).execute();
                }
            });
    }
}