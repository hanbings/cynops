package io.hanbings.cynops.event.interfaces;

import io.hanbings.cynops.event.Event;

import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("unused")
public interface EventBus {
    void setEventHandlerAnnotation(Class<? extends EventHandler> annotation);

    void callEvent(Class<? extends Event> event);

    List<Method> getEventHandler(Class<? extends Event> event);

    void registerEvent(Class<? extends Event> event);

    void unregisterEvent(Class<? extends Event> event);

    void registerListener(Object listener);

    void unregisterListener(Object listener);
}
