package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;

public class AngularDampingBodyEvent extends B2dBaseEvent<AngularDampingBodyEvent> {

    public float angularDamping;

    public AngularDampingBodyEvent() {
        super(B2dEventsEnum.AngularDamping);
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
