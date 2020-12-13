package com.yaroslavm87.testtask01.Vehicle;

public enum VehicleType {
    MOTORCYCLE ("Moto"),
    CAR ("Car"),
    TRUCK ("Truck");

    private String title;

    VehicleType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}


