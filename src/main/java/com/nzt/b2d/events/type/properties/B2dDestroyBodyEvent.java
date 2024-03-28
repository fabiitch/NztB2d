package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;

public class B2dDestroyBodyEvent extends B2dBaseEvent<B2dDestroyBodyEvent> {

    public B2dDestroyBodyEvent() {
        super(B2dEventsEnum.Destroy);
    }

    @Override
    protected boolean canConcat(B2dDestroyBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(B2dDestroyBodyEvent event) {

    }

    @Override
    public void apply(Body body) {
    }

    @Override
    public void reset() {

    }
}
