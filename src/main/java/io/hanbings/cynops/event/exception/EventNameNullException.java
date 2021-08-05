package io.hanbings.cynops.event.exception;

public class EventNameNullException extends NullPointerException {
    public EventNameNullException() {
        super();
    }

    public EventNameNullException(String message) {
        super(message);
    }
}
