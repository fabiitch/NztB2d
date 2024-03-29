package com.nzt.b2d.events;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;

/*
Base class for all Box2D event
 */
@Getter
public abstract class B2dEvent<E extends B2dEvent> implements Poolable {

    private B2dEventsEnum eventType;

    public B2dEvent(B2dEventsEnum eventType) {
        this.eventType = eventType;
    }

    protected abstract boolean canConcat(E event);

    protected abstract void concat(E event);

    public abstract void apply(B2dBody b2dBody);
}
