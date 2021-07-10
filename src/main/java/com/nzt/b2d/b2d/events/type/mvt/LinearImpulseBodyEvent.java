package com.nzt.b2d.b2d.events.type.mvt;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class LinearImpulseBodyEvent extends B2DBaseEvent<LinearImpulseBodyEvent> {

    public Vector2 impulse;
    public Vector2 point;
    public boolean wake;

    public LinearImpulseBodyEvent() {
        super(B2DEventsEnum.LinearImpulse);
    }

    @Override
    public boolean canConcat(LinearImpulseBodyEvent event) {
        return false;
    }

    @Override
    public void doReset() {
        this.impulse.setZero();
        this.point.setZero();
        this.wake = false;
    }

    @Override
    public void apply(Body body) {
        body.applyLinearImpulse(impulse, point, wake);
    }

}
