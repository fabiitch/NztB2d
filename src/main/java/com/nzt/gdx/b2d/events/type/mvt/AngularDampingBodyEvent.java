package com.nzt.gdx.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class AngularDampingBodyEvent extends B2DBaseEvent<AngularDampingBodyEvent> {

    public float angularDamping;

    public AngularDampingBodyEvent() {
        super(B2DEventsEnum.AngularDamping);
    }

    @Override
    public boolean canConcat(AngularDampingBodyEvent event) {
        this.angularDamping = event.angularDamping;
        return true;
    }

    @Override
    public void doReset() {
        this.angularDamping = 0f;
    }

    @Override
    public void apply(Body body) {
        body.setAngularDamping(angularDamping);
    }

}
