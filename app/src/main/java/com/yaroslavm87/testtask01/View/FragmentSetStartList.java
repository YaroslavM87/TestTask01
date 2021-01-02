package com.yaroslavm87.testtask01.View;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yaroslavm87.testtask01.Controller.AddNewVehicleControllerCommand;
import com.yaroslavm87.testtask01.Controller.AddVehicleToStartListControllerCommand;
import com.yaroslavm87.testtask01.Controller.InitializeObjectsControllerCommand;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;
import com.yaroslavm87.testtask01.Notifications.Publisher;
import com.yaroslavm87.testtask01.R;

public class FragmentSetStartList extends Fragment implements View.OnClickListener {

    ActivityTextView vehicleBufferVehicleType;
    TextViewVehicleSpeed vehicleBufferVehicleSpeed;
    ActivityTextView vehicleBufferVehiclePunctureProbability;
    ActivityTextView vehicleBufferVehicleAdditionalValue;

    Button addNewVehicle;
    ButtonVehicleValueConfig reduceVehicleFromBufferValue;
    ButtonVehicleValueConfig increaseVehicleFromBufferValue;
    Button goToSetTrackLength;

    OnVehicleBufferChangedActivityTextViewDataProvider onVehicleBufferChangedActivityTextViewDataProvider;

    RecyclerView recyclerViewVehicleTypes;
    AdapterForRecyclerViewVehicleTypes adapterForRecyclerViewVehicleTypes;

    RecyclerView recyclerViewVehicleStartList;
    AdapterForRecyclerViewStartList adapterForRecyclerViewStartList;
    OnEventAdapterNotifier onEventAdapterNotifier;

    int layoutForRecyclerView;

    InitializeObjectsControllerCommand initializeObjectsControllerCommand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_vehicle_start_list_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeControllerElements();
        initializeViewGroupElements();
        subscribeObjectsForEvents();
    }

    private void initializeControllerElements() {
        this.initializeObjectsControllerCommand = new InitializeObjectsControllerCommand();
        this.initializeObjectsControllerCommand.execute();
    }

    private void initializeViewGroupElements(){
        initializeButtons();
        initializeTextViews();
        initializeRecyclerViewForVehiclesAvailable();
        initializeRecyclerViewForVehicleStartList();

        this.onVehicleBufferChangedActivityTextViewDataProvider =
                new OnVehicleBufferChangedActivityTextViewDataProvider(
                        this.vehicleBufferVehicleType,
                        this.vehicleBufferVehicleSpeed,
                        this.vehicleBufferVehiclePunctureProbability,
                        this.vehicleBufferVehicleAdditionalValue,
                        requireView().findViewById(R.id.vehicleBufferVehicleTypeHeader),
                        requireView().findViewById(R.id.vehicleBufferVehicleSpeedHeader),
                        requireView().findViewById(R.id.vehicleBufferVehiclePunctureProbHeader),
                        requireView().findViewById(R.id.vehicleBufferVehicleAdditionalValueHeader)
                );
    }

    private void initializeButtons() {
        this.addNewVehicle = requireView().findViewById(R.id.addNewVehicle);
        this.addNewVehicle.setOnClickListener(this);

        this.reduceVehicleFromBufferValue = new ButtonVehicleValueConfig(requireView().findViewById(R.id.vehicleValueReduce));
        this.reduceVehicleFromBufferValue.setOnClickListener(this);

        this.increaseVehicleFromBufferValue = new ButtonVehicleValueConfig(requireView().findViewById(R.id.vehicleValueIncrease));
        this.increaseVehicleFromBufferValue.setOnClickListener(this);

        this.goToSetTrackLength = requireView().findViewById(R.id.goToSetTrackLength);
        this.goToSetTrackLength.setOnClickListener(this);
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
    }

    private void initializeRecyclerViewForVehiclesAvailable() {

        int vehicleType = R.id.typeOfVehicleAvailable;

        this.recyclerViewVehicleTypes = requireView().findViewById(R.id.listOfVehiclesAvailable);
        this.layoutForRecyclerView = R.layout.recycler_view_for_list_of_vehicles_available;

        this.adapterForRecyclerViewVehicleTypes = new AdapterForRecyclerViewVehicleTypes(
                this.initializeObjectsControllerCommand.getRaceManager().getListOfVehicleTypes(),
                this.layoutForRecyclerView,
                vehicleType
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

        this.onEventAdapterNotifier = new OnEventAdapterNotifier(adapterForRecyclerViewStartList);

        this.recyclerViewVehicleStartList.setAdapter(adapterForRecyclerViewStartList);
        this.recyclerViewVehicleStartList.setLayoutManager(layoutManagerForRecyclerView);
    }

    private void subscribeObjectsForEvents() {

        Publisher vehicleBufferPublisher =
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getPublisher();

        Publisher vehicleStartListPublisher =
                this.initializeObjectsControllerCommand.getRaceManager().getVehicleStartList().getPublisher();

        vehicleBufferPublisher.subscribeForEvent(
                this.onVehicleBufferChangedActivityTextViewDataProvider,
                EventType.VEHICLE_BUFFER_NEW_VEHICLE_ADDED,
                EventType.VEHICLE_BUFFER_VEHICLE_DELETED
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
                this.onEventAdapterNotifier,
                EventType.VEHICLE_START_LIST_NEW_VEHICLE_ADDED,
                EventType.VEHICLE_START_LIST_VEHICLE_DELETED
        );
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.addNewVehicle:
                addNewVehicle();
                break;

            case R.id.vehicleValueReduce:
                defineActionWhenClickButtonReduceVehicleFromBufferValue();
                break;

            case R.id.vehicleValueIncrease:
                defineActionWhenClickButtonIncreaseVehicleFromBufferValue();
                break;

            case R.id.goToSetTrackLength:
                replaceFragment();
                break;
        }
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
                        getRaceManager().getVehicleBuffer().getVehicleFromBuffer(),
                -10);
    }

    private void defineActionWhenClickButtonIncreaseVehicleFromBufferValue() {
        this.increaseVehicleFromBufferValue.clickButton(
                initializeObjectsControllerCommand.
                        getRaceManager().getVehicleBuffer().getVehicleFromBuffer(),
                10
        );
    }

    private void addNewVehicle() {
        if(this.initializeObjectsControllerCommand.getRaceManager().getVehicleBuffer().getVehicleFromBuffer() == null) {
            Toast toast = Toast.makeText(requireActivity(), "Choose vehicle first", Toast.LENGTH_LONG);
            View view = toast.getView();
            TextView text = view.findViewById(android.R.id.message);
            view.getBackground().setColorFilter(Color.parseColor("#838383"), PorterDuff.Mode.SRC_IN);
            text.setBackgroundColor(Color.parseColor("#838383"));
            text.setTextColor(Color.parseColor("#EAEAEA"));
            toast.show();
        }
        else {
            new AddVehicleToStartListControllerCommand(
                    this.initializeObjectsControllerCommand.getRaceManager()
            ).execute();
        }

    }

    private void replaceFragment() {
        if(initializeObjectsControllerCommand.
                getRaceManager().
                getVehicleStartList().
                getList().
                size() > 1
        ) {
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment, FragmentRace.class, null)
                    .addToBackStack(null)
                    .commit();
        }
    }
}