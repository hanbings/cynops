package io.hanbings.cynops.event.exception;

@SuppressWarnings("unused")
public class EventNameNullException extends NullPointerException {
    public EventNameNullException() {
        super();
    }

    public EventNameNullException(String message) {
        super(message);
    }
}
