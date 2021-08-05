package io.hanbings.cynops.event;

public class Event {
    private String name = null;

    public Event() {

    }

    public Event(String name) {
        this.name = name;
    }

    public String getEventName() {
        return name;
    }
}
