package io.hanbings.cynops.event;

public class TestListener extends Listener{
    public void onEvent(TestEvent event){
        event.test();
    }
}
