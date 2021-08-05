package io.hanbings.cynops.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class EventBus {
    private final Map<Class<? extends Event>, Map<Listener, Method>> handlers = new HashMap<>();
    private Class<? extends Annotation> annotation = EventHandler.class;

    public void setHandlerAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public void callEvent(Event event) {
        if (!handlers.containsKey(event.getClass())) {
            return;
        }
        Map<Listener, Method> methods = handlers.get(event.getClass());
        for (Map.Entry<Listener, Method> entry : methods.entrySet()) {
            try {
                entry.getValue().invoke(entry.getKey(), event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Listener, Method> getEventHandler(Event event) {
        return handlers.get(event.getClass());
    }

    public void registerEvent(Event event) {
        if (!handlers.containsKey(event.getClass())) {
            handlers.put(event.getClass(), new HashMap<>());
        }
    }

    public void unregisterEvent(Event event) {
        handlers.remove(event.getClass());
    }

    public void registerListener(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    handlers.get(event).put(listener, method);
                }
            }
        }
    }

    public void unregisterListener(Listener listener) {
        Class<?> clazz = listener.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                final Class<?> event;
                method.setAccessible(true);
                event = method.getParameterTypes()[0];
                if (handlers.containsKey(event)) {
                    handlers.get(event).remove(listener);
                }
            }
        }
    }
}
