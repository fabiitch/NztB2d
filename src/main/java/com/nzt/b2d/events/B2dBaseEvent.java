package com.nzt.b2d.events;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;
import lombok.Getter;

/*
Base class for all Box2D event
 */
@Getter
public abstract class B2dBaseEvent<E extends B2dBaseEvent> implements Poolable {

    /*
    utilisé pour le check destroy et le concat
     */
    private final short eventType;

    public B2dBaseEvent(short eventType) {
        this.eventType = eventType;
    }

    public B2dBaseEvent(B2dEventsEnum event) {
        this((short) event.ordinal());
    }

    protected abstract boolean canConcat(E event);

    protected abstract void concat(E event);

    public abstract void apply(Body body);
}
