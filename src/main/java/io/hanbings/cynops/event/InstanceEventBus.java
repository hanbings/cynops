package io.hanbings.cynops.event;

import io.hanbings.cynops.event.interfaces.EventBus;
import io.hanbings.cynops.event.interfaces.EventHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class InstanceEventBus implements EventBus {
    private final Map<Event, List<Method>> handlers = new HashMap<>();
    private EventHandler annotation = null;

    @Override
    public void setEventHandlerAnnotation(EventHandler annotation) {
        this.annotation = annotation;
    }

    @Override
    public void callEvent(Event event) {
        if (!handlers.containsKey(event)) {
            return;
        }
        List<Method> methods = handlers.get(event);
        for (Method method : methods) {
            try {
                method.invoke(event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Method> getEventHandler(Event event) {
        return handlers.get(event);
    }

    @Override
    public void registerEvent(Event event) {
        if (!handlers.containsKey(event)) {
            handlers.put(event, new ArrayList<>());
        }
    }

    @Override
    public void unregisterEvent(Event event) {
        handlers.remove(event);
    }

    @Override
    public void registerListener(Object listener) {
    }

    @Override
    public void unregisterListener(Object listener) {
    }
}
