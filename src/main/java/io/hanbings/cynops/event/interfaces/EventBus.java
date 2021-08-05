package io.hanbings.cynops.event.interfaces;

import io.hanbings.cynops.event.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("unused")
public interface EventBus {
    void setEventHandlerAnnotation(Annotation annotation);

    void callEvent(Class<? extends Event> event);

    List<Method> getEventHandler(Class<? extends Event> event);

    void registerEvent(Class<? extends Event> event);

    void unregisterEvent(Class<? extends Event> event);

    void registerListener(Class<?> listener);

    void unregisterListener(Class<?> listener);
}
