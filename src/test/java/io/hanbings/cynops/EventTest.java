package io.hanbings.cynops;

import io.hanbings.cynops.event.*;

public class EventTest {
    public static void main(String[] args){
        EventBus eventBus = new EventBus();
        TestEvent testEvent = new TestEvent();
        TestListener testListener = new TestListener();
        eventBus.setHandlerAnnotation(TestEventHandler.class);
        eventBus.registerEvent(testEvent);
        eventBus.registerListener(testListener);
        eventBus.callEvent(testEvent);
    }
}
