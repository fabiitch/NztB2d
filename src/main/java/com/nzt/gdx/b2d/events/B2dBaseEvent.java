package com.nzt.gdx.b2d.events;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

/*
Base class for all Box2D event
 */
public abstract class B2dBaseEvent<E extends B2dBaseEvent> implements Poolable {

    /*
    utilisé pour le check destroy et le concat
     */
    public final short eventType;

    public B2dBaseEvent(short eventType) {
        this.eventType = eventType;
    }

    public B2dBaseEvent(B2dEventsEnum event) {
        this((short) event.ordinal());
    }

    protected abstract boolean canConcat(E event);

    @Override
    public void reset() {
        doReset();
    }

    protected abstract void doReset();

    public abstract void apply(Body body);
}
