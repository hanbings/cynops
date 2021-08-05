package io.hanbings.cynops.event;

import io.hanbings.cynops.event.interfaces.EventBus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ChristenEventBus implements EventBus {
    private final Map<String, List<Method>> handlers = new HashMap<>();
    private Annotation annotation = null;

    @Override
    public void setEventHandlerAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    @Override
    public void callEvent(Class<? extends Event> event) {
        if (!handlers.containsKey(event.getName())) {
            return;
        }
        List<Method> methods = handlers.get(event.getName());
        for (Method method : methods) {
            try {
                method.invoke(event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Method> getEventHandler(Class<? extends Event> event) {
        return handlers.get(event.getName());
    }

    @Override
    public void registerEvent(Class<? extends Event> event) {
        if (!handlers.containsKey(event.getName())) {
            handlers.put(event.getName(), new ArrayList<>());
        }
    }

    @Override
    public void unregisterEvent(Class<? extends Event> event) {
        handlers.remove(event.getName());
    }

    @Override
    public void registerListener(Class<?> listener) {

    }

    @Override
    public void unregisterListener(Class<?> listener) {

    }
}
