package com.yaroslavm87.testtask01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yaroslavm87.testtask01.View.FragmentSetStartList;

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
                .add(R.id.fragment, FragmentSetStartList.class, null)
                .addToBackStack(null)
                .commit();
    }
}