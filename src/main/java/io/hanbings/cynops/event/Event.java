package io.hanbings.cynops.event;

import io.hanbings.cynops.event.exception.EventNameNullException;

@SuppressWarnings("unused")
public class Event {
    private String name = null;

    public Event() {

    }

    public Event(String name) {
        this.name = name;
    }

    public String getEventName() {
        if (name == null){
            throw new EventNameNullException("Event Name is Null");
        } else {
            return name;
        }
    }
}
