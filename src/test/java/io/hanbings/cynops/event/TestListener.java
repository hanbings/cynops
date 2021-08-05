package io.hanbings.cynops.event;

public class TestListener extends Listener{
    @TestEventHandler
    public void onEvent(TestEvent event){
        event.test();
    }
}
