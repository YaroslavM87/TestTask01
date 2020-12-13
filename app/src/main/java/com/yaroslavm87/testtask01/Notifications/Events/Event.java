package com.yaroslavm87.testtask01.Notifications.Events;

public abstract class Event {

    EventType type;

    public Event(EventType type){
        this.type = type;
    }

    public EventType getType() {
        return this.type;
    }
}