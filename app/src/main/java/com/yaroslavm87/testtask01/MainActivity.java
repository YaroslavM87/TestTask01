package com.yaroslavm87.testtask01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yaroslavm87.testtask01.Vehicle.Vehicle;
import com.yaroslavm87.testtask01.View.FragmentPreRace;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewGroupElements();
    }

    private void initializeViewGroupElements() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_vehicles_available, FragmentPreRace.class, null)
                .addToBackStack(null)
                .commit();
    }


    //    private void initializeButtons() {
//        this.addNewVehicle = findViewById(R.id.addNewVehicle);
//        this.reduceTrackLength = findViewById(R.id.trackLengthReduce);
//        this.increaseTrackLength = findViewById(R.id.trackLengthIncrease);
//        this.reduceVehicleFromBufferValue = new ButtonConfig(findViewById(R.id.vehicleValueReduce));
//        this.increaseVehicleFromBufferValue = new ButtonConfig(findViewById(R.id.vehicleValueIncrease));
//    }
//
//    private void initializeTextViews() {
//        this.vehicleBufferVehicleType = new VehicleTypeTextView(
//                (TextView) findViewById(R.id.vehicleBufferVehicleType)
//        );
//
//        this.vehicleBufferVehicleSpeed = new TextViewVehicleSpeed(
//                (TextView) findViewById(R.id.vehicleBufferVehicleSpeed)
//        );
//        this.vehicleBufferVehicleSpeed.getTextView().setOnClickListener(this.vehicleBufferVehicleSpeed);
//        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.increaseVehicleFromBufferValue);
//        this.vehicleBufferVehicleSpeed.setOnActivityTextViewClickListener(this.reduceVehicleFromBufferValue);
//
//        this.vehicleBufferVehiclePunctureProbability = new VehiclePunctureProbabilityTextView(
//                (TextView) findViewById(R.id.vehicleBufferVehiclePunctureProb)
//        );
//
//        this.vehicleBufferVehicleAdditionalValue = new VehicleAdditionalValueTextView(
//                (TextView) findViewById(R.id.vehicleBufferVehicleAdditionalValue)
//        );
//
//        this.trackLength = new TrackLengthTextView(
//                (TextView) findViewById(R.id.trackLength)
//        );
//
//        this.trackLength.receiveUpdate(this.initializeObjectsControllerCommand.getRaceManager().getTrackLength());
//    }
//
//    private void initializeRecyclerViewForVehiclesAvailable() {
//
//        int vehicleType = R.id.typeOfVehicleAvailable;
//        int empty = R.id.emptyTextView;
//
//        this.recyclerViewVehicleTypes = findViewById(R.id.listOfVehiclesAvailable);
//        this.layoutForRecyclerView = R.layout.recycler_view_for_list_of_vehicles_available;
//
//        this.adapterForRecyclerViewVehicleTypes = new AdapterForRecyclerViewVehicleTypes(
//                this.initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes(),
//                this.layoutForRecyclerView,
//                vehicleType,
//                empty
//        );
//
//        this.layoutManagerForRecyclerView = new LinearLayoutManager(
//                this,
//                LinearLayoutManager.HORIZONTAL,
//                false
//        );
//
//        this.recyclerViewVehicleTypes.hasFixedSize();
//        this.recyclerViewVehicleTypes.setAdapter(adapterForRecyclerViewVehicleTypes);
//        this.recyclerViewVehicleTypes.setLayoutManager(layoutManagerForRecyclerView);
//
//        defineActionWhenClickVehicleTypesAvailableListItem(adapterForRecyclerViewVehicleTypes);
//    }
//
//    private void initializeRecyclerViewForVehicleStartList() {
//
//        int vehicleType = R.id.vehicleStartListTextViewType;
//        int vehicleSpeed = R.id.vehicleStartListTextViewVehicleSpeed;
//        int vehicleDistanceTravelled = R.id.vehicleStartListTextViewVehicleDistanceTravelled;
//
//        this.recyclerViewVehicleStartList = findViewById(R.id.listOfVehicleStartList);
//        this.layoutForRecyclerView = R.layout.recycler_view_for_vehicle_start_list;
//
//
//        this.adapterForRecyclerViewStartList = new AdapterForRecyclerViewStartList(
//                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getList(),
//                this.layoutForRecyclerView,
//                vehicleType,
//                vehicleSpeed,
//                vehicleDistanceTravelled,
//                this.initializeObjectsControllerCommand.getRaceManager().getPublisher()
//        );
//
//        this.layoutManagerForRecyclerView = new LinearLayoutManager(this);
//
//        this.adapterNotifier = new AdapterNotifier(adapterForRecyclerViewStartList);
//
//        this.recyclerViewVehicleStartList.setAdapter(adapterForRecyclerViewStartList);
//        this.recyclerViewVehicleStartList.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    private void subscribeObjectsForEvents() {
//
//        Publisher vehicleRaceManagerPublisher =
//                this.initializeObjectsControllerCommand.getRaceManager().getPublisher();
//
//        Publisher vehicleBufferPublisher =
//                this.initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getPublisher();
//
//        Publisher vehicleStartListPublisher =
//                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getPublisher();
//
//        vehicleRaceManagerPublisher.subscribeForEvent(
//                this.trackLength,
//                EventType.RACE_MANAGER_VALUE_CHANGED_TRACK_LENGTH
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
//                this.vehicleBufferVehicleType
////                this.vehicleBufferVehicleSpeed,
////                this.vehicleBufferVehiclePunctureProbability,
////                this.vehicleBufferVehicleAdditionalValue
//        );
//
//        vehicleBufferPublisher.subscribeForEvent(
//                EventType.VEHICLE_BUFFER_VEHICLE_DELETED,
//                this.vehicleBufferVehicleType
////                this.vehicleBufferVehicleSpeed,
////                this.vehicleBufferVehiclePunctureProbability,
////                this.vehicleBufferVehicleAdditionalValue
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
//    }
//
//    public void addNewVehicle(View addNewVehicle) {
//        new AddVehicleToStartListControllerCommand(
//                this.initializeObjectsControllerCommand.getRaceManager()
//        ).execute();
//    }
//
//    public void reduceTrackLength(View addNewVehicle) {
//        new SetTrackLengthControllerCommand(this.initializeObjectsControllerCommand.getRaceManager(), -100).execute();
//    }
//
//    public void increaseTrackLength(View addNewVehicle) {
//        new SetTrackLengthControllerCommand(this.initializeObjectsControllerCommand.getRaceManager(), 100).execute();
//    }
//
//    public void startRace(View addNewVehicle) {
//        new StartRaceControllerCommand(this.initializeObjectsControllerCommand.getRaceManager()).execute();
//    }
//
//    public void defineActionWhenClickVehicleTypesAvailableListItem(
//            AdapterForRecyclerViewVehicleTypes adapter
//    ) {
//        adapter.setOnEntryClickListener(
//                new AdapterForRecyclerViewVehicleTypes.OnEntryClickListener() {
//                @Override
//                public void onEntryClick(View view, int position) {
//                    new AddNewVehicleControllerCommand(
//                            initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes().get(position),
//                            initializeObjectsControllerCommand.getRaceManager()
//                    ).execute();
//                }
//            });
//    }
//
//    public void defineActionWhenClickButtonReduceVehicleFromBufferValue(View v) {
//        this.reduceVehicleFromBufferValue.clickButton(
//                initializeObjectsControllerCommand.
//                        getRaceManager().
//                        getVehicleBuffer().
//                        getVehicleFromBuffer(),
//                -10);
//    }
//
//    public void defineActionWhenClickButtonIncreaseVehicleFromBufferValue(View v) {
//        this.increaseVehicleFromBufferValue.clickButton(
//                initializeObjectsControllerCommand.
//                        getRaceManager().
//                        getVehicleBuffer().
//                        getVehicleFromBuffer(),
//                10);
//    }
}