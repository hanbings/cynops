package io.hanbings.cynops.event.interfaces;

import io.hanbings.cynops.event.Event;

import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("unused")
public interface EventBus {
    void setEventHandlerAnnotation(EventHandler annotation);

    void callEvent(Event event);

    List<Method> getEventHandler(Event event);

    void registerEvent(Event event);

    void unregisterEvent(Event event);

    void registerListener(Object listener);

    void unregisterListener(Object listener);
}
