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
public class SingletonEventBus implements EventBus {
    private final Map<Class<? extends Event>, List<Method>> handlers = new HashMap<>();
    private Class<? extends EventHandler> annotation = null;

    public void setEventHandlerAnnotation(Class<? extends EventHandler> annotation) {
        this.annotation = annotation;
    }

    public void callEvent(Class<? extends Event> event) {
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

    public List<Method> getEventHandler(Class<? extends Event> event) {
        return handlers.get(event);
    }

    public void registerEvent(Class<? extends Event> event) {
        if (!handlers.containsKey(event)) {
            handlers.put(event, new ArrayList<>());
        }
    }

    public void unregisterEvent(Class<? extends Event> event) {
        handlers.remove(event);
    }

    public void registerListener(Object listener) {
    }

    public void unregisterListener(Object listener) {
    }
}
