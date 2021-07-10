package com.nzt.b2d.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class AngularVelocityEvent extends B2DBaseEvent<AngularVelocityEvent> {

    public float angularVelocity;

    public AngularVelocityEvent() {
        super(B2DEventsEnum.AngularVelocity);
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
