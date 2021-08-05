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
public class ChristenEventBus implements EventBus {
    private final Map<String, List<Method>> handlers = new HashMap<>();
    private EventHandler annotation = null;

    @Override
    public void setEventHandlerAnnotation(EventHandler annotation) {
        this.annotation = annotation;
    }

    @Override
    public void callEvent(Event event) {
        if (!handlers.containsKey(event.getEventName())) {
            return;
        }
        List<Method> methods = handlers.get(event.getEventName());
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
        return handlers.get(event.getEventName());
    }

    @Override
    public void registerEvent(Event event) {
        if (!handlers.containsKey(event.getEventName())) {
            handlers.put(event.getEventName(), new ArrayList<>());
        }
    }

    @Override
    public void unregisterEvent(Event event) {
        handlers.remove(event.getEventName());
    }

    @Override
    public void registerListener(Object listener) {

    }

    @Override
    public void unregisterListener(Object listener) {

    }
}
