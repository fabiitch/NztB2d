package com.nzt.gdx.b2d.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/*
contains event for body
 */
public class B2dEventContainer implements Pool.Poolable {
    public final Array<B2dBaseEvent> eventArray;

    public B2dEventContainer() {
        this.eventArray = new Array<>();
    }

    @Override
    public void reset() {
        this.eventArray.clear();
    }

    public void addEvent(B2dBaseEvent... addEvents) {
        for (B2dBaseEvent event : addEvents) {
            addEvent(event);
        }
    }

    public void addEvent(B2dBaseEvent addEvent) {
        boolean found = false;
        for (B2dBaseEvent event : eventArray) {
            if (event.eventType == addEvent.eventType) {
                found = true;
                boolean canConcat = event.canConcat(addEvent);
                if (canConcat) {
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
