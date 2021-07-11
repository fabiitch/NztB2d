package com.nzt.gdx.b2d.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/*
contains event for body
 */
public class B2DEventContainer implements Pool.Poolable {
    public final Array<B2DBaseEvent> eventArray;

    public B2DEventContainer() {
        this.eventArray = new Array<>();
    }

    @Override
    public void reset() {
        this.eventArray.clear();
    }

    public void addEvent(B2DBaseEvent... addEvents) {
        for (B2DBaseEvent event : addEvents) {
            addEvent(event);
        }
    }

    public void addEvent(B2DBaseEvent addEvent) {
        boolean found = false;
        for (B2DBaseEvent event : eventArray) {
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
