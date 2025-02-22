package com.julien.plop.event.adapter;

import com.julien.plop.event.domain.Event;
import com.julien.plop.event.domain.EventOutput;
import org.springframework.stereotype.Component;

@Component
public class EventFakeAdapter implements EventOutput {
    @Override
    public void fire(Event event) {
        System.out.println(event);
    }

}
