package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;

public class DestroyBodyEvent extends B2dEvent<DestroyBodyEvent> {

    public DestroyBodyEvent() {
        super(B2dEventsEnum.Destroy);
    }

    @Override
    protected boolean canConcat(DestroyBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(DestroyBodyEvent event) {

    }

    @Override
    public void apply(B2dBody b2dBody) {
        Body body = b2dBody.getBody();
        b2dBody.getB2dWorld().destroyBody(b2dBody);

    }

    @Override
    public void reset() {

    }
}
