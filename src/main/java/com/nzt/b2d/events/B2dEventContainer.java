package com.nzt.b2d.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.b2d.events.type.properties.B2dDestroyBodyEvent;
import lombok.Getter;
import lombok.Setter;

/*
contains event for body
 */
@Getter
@Setter
public class B2dEventContainer implements Pool.Poolable {
    private final Array<B2dBaseEvent> eventArray;
    private boolean deleteBodyFlag;

    public B2dEventContainer() {
        this.eventArray = new Array<>();
    }

    @Override
    public void reset() {
        this.eventArray.clear();
    }

    public void addEvent(B2dDestroyBodyEvent destroyEvent) {
        deleteBodyFlag = true;
    }

    public void addEvent(B2dBaseEvent addEvent) {
        boolean found = false;
        for (B2dBaseEvent event : eventArray) {
            if (event.getEventType() == addEvent.getEventType()) {
                found = true;
                if (event.canConcat(addEvent)) {
                    Pools.free(addEvent);
                } else {
                    eventArray.add(addEvent);
                }
                break;
            }
        }

        if (!found) {
            eventArray.add(addEvent);
        }
    }
}
