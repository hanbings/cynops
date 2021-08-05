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
public class InstanceEventBus implements EventBus {
    private final Map<Class<? extends Event>, List<Method>> handlers = new HashMap<>();
    private Annotation annotation = null;

    @Override
    public void setEventHandlerAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    @Override
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

    @Override
    public List<Method> getEventHandler(Class<? extends Event> event) {
        return handlers.get(event);
    }

    @Override
    public void registerEvent(Class<? extends Event> event) {
        if (!handlers.containsKey(event)) {
            handlers.put(event, new ArrayList<>());
        }
    }

    @Override
    public void unregisterEvent(Class<? extends Event> event) {
        handlers.remove(event);
    }

    @Override
    public void registerListener(Class<?> listener) {
        for (Method method : listener.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation.getClass())) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    handlers.get(event).add(method);
                }
            }
        }
    }

    @Override
    public void unregisterListener(Class<?> listener) {
        for (Method method : listener.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation.getClass())) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    handlers.get(event).remove(method);
                }
            }
        }
    }
}
