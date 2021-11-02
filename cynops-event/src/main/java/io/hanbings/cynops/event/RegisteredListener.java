/*
 * Copyright (c) 2021 Hanbings / hanbings Cynops Toolbox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.cynops.event;

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
            case HIGH:
                return 3;
            case HIGHEST:
                return 4;
            case MONITOR:
                return 5;
            default:
                return 2;
        }
    }
}
