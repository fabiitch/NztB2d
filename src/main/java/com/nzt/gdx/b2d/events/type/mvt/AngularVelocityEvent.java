package com.nzt.gdx.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2dBaseEvent;
import com.nzt.gdx.b2d.events.B2dEventsEnum;

public class AngularVelocityEvent extends B2dBaseEvent<AngularVelocityEvent> {

    public float angularVelocity;

    public AngularVelocityEvent() {
        super(B2dEventsEnum.AngularVelocity);
    }

    @Override
    public boolean canConcat(AngularVelocityEvent event) {
        this.angularVelocity += event.angularVelocity;
        return true;
    }

    @Override
    public void doReset() {
        this.angularVelocity = 0f;
    }

    @Override
    public void apply(Body body) {
        body.setAngularVelocity(angularVelocity);
    }

}
