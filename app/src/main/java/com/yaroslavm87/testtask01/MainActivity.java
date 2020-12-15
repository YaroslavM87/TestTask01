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
import com.yaroslavm87.testtask01.View.ButtonConfig;
import com.yaroslavm87.testtask01.View.TrackLengthTextView;
import com.yaroslavm87.testtask01.View.VehicleAdditionalValueTextView;
import com.yaroslavm87.testtask01.View.VehiclePunctureProbabilityTextView;
import com.yaroslavm87.testtask01.View.TextViewVehicleSpeed;
import com.yaroslavm87.testtask01.View.VehicleTypeTextView;

public class MainActivity extends AppCompatActivity {

    ActivityTextView vehicleBufferVehicleType;
    TextViewVehicleSpeed vehicleBufferVehicleSpeed;
    ActivityTextView vehicleBufferVehiclePunctureProbability;
    ActivityTextView vehicleBufferVehicleAdditionalValue;
    ActivityTextView trackLength;
    Button addNewVehicle;
    Button reduceTrackLength;
    Button increaseTrackLength;
    ButtonConfig reduceVehicleFromBufferValue;
    ButtonConfig increaseVehicleFromBufferValue;
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
        this.initializeObjectsControllerCommand = new InitializeObjectsControllerCommand();
        this.initializeObjectsControllerCommand.execute();
    }

    private void initializeViewGroupElements() {
        initializeButtons();
        initializeTextViews();
        initializeRecyclerViewForVehiclesAvailable();
        initializeRecyclerViewForVehicleStartList();
    }

    private void initializeButtons() {
        this.addNewVehicle = findViewById(R.id.addNewVehicle);
        this.reduceTrackLength = findViewById(R.id.trackLengthReduce);
        this.increaseTrackLength = findViewById(R.id.trackLengthIncrease);
        this.reduceVehicleFromBufferValue = new ButtonConfig(findViewById(R.id.vehicleValueReduce));
        this.increaseVehicleFromBufferValue = new ButtonConfig(findViewById(R.id.vehicleValueIncrease));
    }

    private void initializeTextViews() {
        this.vehicleBufferVehicleType = new VehicleTypeTextView(
                (TextView) findViewById(R.id.vehicleBufferVehicleType)
        );

        this.vehicleBufferVehicleSpeed = new TextViewVehicleSpeed(
                (TextView) findViewById(R.id.vehicleBufferVehicleSpeed)
        );
        this.vehicleBufferVehicleSpeed.getTextView().setOnClickListener(this.vehicleBufferVehicleSpeed);
        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.increaseVehicleFromBufferValue);
        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.reduceVehicleFromBufferValue);

        this.vehicleBufferVehiclePunctureProbability = new VehiclePunctureProbabilityTextView(
                (TextView) findViewById(R.id.vehicleBufferVehiclePunctureProb)
        );

        this.vehicleBufferVehicleAdditionalValue = new VehicleAdditionalValueTextView(
                (TextView) findViewById(R.id.vehicleBufferVehicleAdditionalValue)
        );

        this.trackLength = new TrackLengthTextView(
                (TextView) findViewById(R.id.trackLength)
        );

        this.trackLength.receiveUpdate(this.initializeObjectsControllerCommand.getRaceManager().getTrackLength());
    }

    private void initializeRecyclerViewForVehiclesAvailable() {

        int vehicleType = R.id.typeOfVehicleAvailable;
        int empty = R.id.emptyTextView;

        this.recyclerViewVehicleTypes = findViewById(R.id.listOfVehiclesAvailable);
        this.layoutForRecyclerView = R.layout.recycler_view_for_list_of_vehicles_available;

        this.adapterForRecyclerViewVehicleTypes = new AdapterForRecyclerViewVehicleTypes(
                this.initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes(),
                this.layoutForRecyclerView,
                vehicleType,
                empty
        );

        this.layoutManagerForRecyclerView = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );

        this.recyclerViewVehicleTypes.hasFixedSize();
        this.recyclerViewVehicleTypes.setAdapter(adapterForRecyclerViewVehicleTypes);
        this.recyclerViewVehicleTypes.setLayoutManager(layoutManagerForRecyclerView);

        defineActionWhenClickVehicleTypesAvailableListItem(adapterForRecyclerViewVehicleTypes);
    }

    private void initializeRecyclerViewForVehicleStartList() {

        int vehicleType = R.id.vehicleStartListTextViewType;
        int vehicleSpeed = R.id.vehicleStartListTextViewVehicleSpeed;
        int vehicleDistanceTravelled = R.id.vehicleStartListTextViewVehicleDistanceTravelled;

        this.recyclerViewVehicleStartList = findViewById(R.id.listOfVehicleStartList);
        this.layoutForRecyclerView = R.layout.recycler_view_for_vehicle_start_list;


        this.adapterForRecyclerViewStartList = new AdapterForRecyclerViewStartList(
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getList(),
                this.layoutForRecyclerView,
                vehicleType,
                vehicleSpeed,
                vehicleDistanceTravelled,
                this.initializeObjectsControllerCommand.getRaceManager().getPublisher()
        );

        this.layoutManagerForRecyclerView = new LinearLayoutManager(this);

        this.adapterNotifier = new AdapterNotifier(adapterForRecyclerViewStartList);

        this.recyclerViewVehicleStartList.setAdapter(adapterForRecyclerViewStartList);
        this.recyclerViewVehicleStartList.setLayoutManager(new LinearLayoutManager(this));
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

        vehicleBufferPublisher.subscribeForEvent(
                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
                this.vehicleBufferVehicleType
//                this.vehicleBufferVehicleSpeed,
//                this.vehicleBufferVehiclePunctureProbability,
//                this.vehicleBufferVehicleAdditionalValue
        );

        vehicleBufferPublisher.subscribeForEvent(
                EventType.VEHICLE_BUFFER_VEHICLE_DELETED,
                this.vehicleBufferVehicleType
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

    public void addNewVehicle(View addNewVehicle) {
        new AddVehicleToStartListControllerCommand(
                this.initializeObjectsControllerCommand.getRaceManager()
        ).execute();
    }

    public void reduceTrackLength(View addNewVehicle) {
        new SetTrackLengthControllerCommand(this.initializeObjectsControllerCommand.getRaceManager(), -100).execute();
    }

    public void increaseTrackLength(View addNewVehicle) {
        new SetTrackLengthControllerCommand(this.initializeObjectsControllerCommand.getRaceManager(), 100).execute();
    }

    public void startRace(View addNewVehicle) {
        new StartRaceControllerCommand(this.initializeObjectsControllerCommand.getRaceManager()).execute();
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

    public void defineActionWhenClickButtonReduceVehicleFromBufferValue(View v) {
        this.reduceVehicleFromBufferValue.clickButton(
                initializeObjectsControllerCommand.
                        getRaceManager().
                        getVehicleBuffer().
                        getVehicleFromBuffer(),
                -10);
    }

    public void defineActionWhenClickButtonIncreaseVehicleFromBufferValue(View v) {
        this.increaseVehicleFromBufferValue.clickButton(
                initializeObjectsControllerCommand.
                        getRaceManager().
                        getVehicleBuffer().
                        getVehicleFromBuffer(),
                10);
    }
}