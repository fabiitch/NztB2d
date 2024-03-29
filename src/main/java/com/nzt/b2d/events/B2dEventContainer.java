package com.nzt.b2d.events;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.b2d.events.type.fixture.B2dFixtureEvent;
import com.nzt.b2d.wrapper.B2dBody;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;

/*
contains event for body
 */

public class B2dEventContainer implements Pool.Poolable {
    @Getter
    private final Array<B2dEvent> events = new Array<>();
    private final Array<B2dEvent> toRemoveInternal = new Array<>();

    @Override
    public void reset() {
        this.events.clear();
    }

    public void process(B2dBody b2dBody) {
        for (int i = 0, n = events.size; i < n; i++) {
            if (b2dBody.isDestroyed())
                break;
            events.get(i).apply(b2dBody);
        }
    }

    public void concatEvents() {
        //TODO concat all at end
    }

    public void addEvent(B2dEvent addEvent) {
        boolean found = false;
        for (B2dEvent event : events) {
            if (event.getEventType() == addEvent.getEventType()) {
                found = true;
                if (event.canConcat(addEvent)) {
                    Pools.free(addEvent);
                } else {
                    events.add(addEvent);
                }
                break;
            }
        }

        if (!found) {
            events.add(addEvent);
        }
    }

    public void removeEventsForFixture(B2dFixture fixture) {
        for (int i = 0, n = events.size; i < n; i++) {
            B2dEvent b2dEvent = events.get(i);

            if (b2dEvent.getEventType().getTarget() == B2dEventTarget.Fixture) {
                B2dFixtureEvent fixtureEvent = (B2dFixtureEvent) b2dEvent;

                if (fixtureEvent.getB2dFixture() == fixture) {
                    toRemoveInternal.add(fixtureEvent);
                }
            }
        }
        events.removeAll(toRemoveInternal, true);
        toRemoveInternal.clear();
    }
}
