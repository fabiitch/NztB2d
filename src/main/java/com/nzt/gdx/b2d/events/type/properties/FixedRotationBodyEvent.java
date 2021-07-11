package com.nzt.gdx.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class FixedRotationBodyEvent extends B2DBaseEvent<FixedRotationBodyEvent> {

    public boolean fixedRotation;

    public FixedRotationBodyEvent() {
        super(B2DEventsEnum.AngularDamping);
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
