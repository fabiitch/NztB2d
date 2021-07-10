package com.nzt.b2d.b2d.events;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

/*
Base class for all Box2D event
 */
public abstract class B2DBaseEvent<E extends B2DBaseEvent> implements Poolable {

    /*
    utilisé pour le check destroy et le concat
     */
    public final short eventType;

    public B2DBaseEvent(short eventType) {
        this.eventType = eventType;
    }

    public B2DBaseEvent(B2DEventsEnum event) {
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
