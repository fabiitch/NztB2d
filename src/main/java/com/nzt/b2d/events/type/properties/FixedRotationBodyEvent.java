package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;

public class FixedRotationBodyEvent extends B2dBaseEvent<FixedRotationBodyEvent> {

    public boolean fixedRotation;

    public FixedRotationBodyEvent() {
        super(B2dEventsEnum.AngularDamping);
    }

    @Override
    public boolean canConcat(FixedRotationBodyEvent event) {
        return true;
    }

    @Override
    public void doReset() {
        fixedRotation = false;
    }

    @Override
    public void apply(Body body) {
        body.setFixedRotation(fixedRotation);
    }

}
