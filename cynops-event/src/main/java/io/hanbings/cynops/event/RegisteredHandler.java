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

import io.hanbings.cynops.event.interfaces.Listener;

import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class RegisteredHandler {
    private EventPriority priority;
    private boolean ignoreCancelled;
    private Listener listener;
    private Method method;

    private RegisteredHandler() {
    }

    public RegisteredHandler(EventPriority priority, boolean ignoreCancelled
            , Listener listener, Method method) {
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
        this.listener = listener;
        this.method = method;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }

    public Listener getListener() {
        return listener;
    }

    public Method getMethod() {
        return method;
    }
}
