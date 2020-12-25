package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Vehicle.Car;

public class SetAmountOfPassengersModelCommand extends ModelCommand {

    private Car car;
    private int amountOfPassengers;

    public SetAmountOfPassengersModelCommand(Car car, int amountOfPassengers) {
        super();
        this.car = car;
        this.amountOfPassengers = amountOfPassengers;
    }

    @Override
    public void execute() {
        car.setAmountOfPassengers(amountOfPassengers);
        super.markAsExecuted();
    }

    // TODO: add check for param value

}