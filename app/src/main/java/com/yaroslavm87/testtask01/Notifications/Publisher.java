package com.yaroslavm87.testtask01.Notifications;

import com.yaroslavm87.testtask01.Notifications.Events.Event;
import com.yaroslavm87.testtask01.Notifications.Events.EventType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Publisher {

    private Map<EventType, List<Subscriber>> listsOfSubscribers;

    public Publisher() {
        this.listsOfSubscribers = new HashMap<>(6);
    }

    public void subscribeForEvent(EventType eventType, Subscriber ...subscribers) {
        for(Subscriber s : subscribers) {
            getAppropriateListOfSubscribers(eventType).add(s);
        }
    }

    public void subscribeForEvent(Subscriber subscriber, EventType ...eventTypes) {
        for(EventType et : eventTypes) {
            getAppropriateListOfSubscribers(et).add(subscriber);
        }
    }

    public void notifyEventHappened(Observable observable, Event event) {
        notifySubscribers(
                observable,
                getAppropriateListOfSubscribers(event.getType()),
                event
        );
    }

    public boolean cancelSubscription(Subscriber subscriber, EventType eventType) {

        if(getAppropriateListOfSubscribers(eventType).size() != 0) {
            List<Subscriber> list = getAppropriateListOfSubscribers(eventType);

            boolean result = list.remove(subscriber);

            if(list.size() == 0) {
                this.listsOfSubscribers.remove(eventType);
            }
            return result;

        } else {
            return false;
        }
    }

    private List<Subscriber> getAppropriateListOfSubscribers(EventType eventType) {

        if (listsOfSubscribers.containsKey(eventType)) {
            return listsOfSubscribers.get(eventType);

        } else {
            List<Subscriber> list = new LinkedList<>();
            this.listsOfSubscribers.put(eventType, list);
            return list;
        }
    }

    private void notifySubscribers(Observable observable, List<Subscriber> list, Event event) {
        if(list != null) {
            for (Subscriber subscriber : list) {
                if(subscriber != null) {
                    observable.prepareCommandForUpdate(event, subscriber).execute();
                }
            }
        }
    }
}