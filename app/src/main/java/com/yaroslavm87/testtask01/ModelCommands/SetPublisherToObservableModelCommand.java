package com.yaroslavm87.testtask01.ModelCommands;

import com.yaroslavm87.testtask01.Notifications.Observable;
import com.yaroslavm87.testtask01.Notifications.Publisher;

public class SetPublisherToObservableModelCommand extends ModelCommand {

    private Observable observable;
    private Publisher publisher;

    public SetPublisherToObservableModelCommand(Observable observable, Publisher publisher) {
        super();
        this.observable = observable;
        this.publisher = publisher;
    }

    @Override
    public void execute() {
        this.observable.setTimerPublisher(this.publisher);
        super.markAsExecuted();
    }
}