package com.yaroslavm87.testtask01.Notifications;

import com.yaroslavm87.testtask01.ModelCommands.ModelCommand;
import com.yaroslavm87.testtask01.Notifications.Events.Event;

public interface Observable {

    ModelCommand prepareCommandForUpdate(Event event, Subscriber subscriber);

    void setPublisher(Publisher timerPublisher);
}