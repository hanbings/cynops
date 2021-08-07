package io.hanbings.cynops.event;

import io.hanbings.cynops.event.interfaces.EventPriority;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RegisteredListener {
    private final List<RegisteredHandler> handlerList = new ArrayList<>();
    private final List<Integer> priorityIndex = new ArrayList<>();

    public RegisteredListener() {
        for (int count = 0; count < 6; count++) {
            priorityIndex.add(0);
        }
    }

    public List<RegisteredHandler> getHandlerList() {
        return handlerList;
    }

    public void addHandler(RegisteredHandler handler) {
        // 这一处非常糟糕的说
        int priority = getPriorityShadow(handler.getPriority());
        handlerList.add(priorityIndex.get(priority), handler);
        for (int count = priority; count < 6; count++) {
            priorityIndex.set(count, priorityIndex.get(count) + 1);
        }
    }

    public void removeHandler(RegisteredHandler handler) {
        handlerList.removeIf(registeredHandler -> registeredHandler.getListener().equals(handler.getListener()));
    }

    private int getPriorityShadow(EventPriority priority) {
        switch (priority) {
            case LOWEST:
                return 0;
            case LOW:
                return 1;
            case NORMAL:
                return 2;
            case HIGH:
                return 3;
            case HIGHEST:
                return 4;
            case MONITOR:
                return 5;
        }
        return 2;
    }
}
