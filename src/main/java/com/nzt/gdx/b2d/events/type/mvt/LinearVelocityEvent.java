package com.nzt.gdx.b2d.events.type.mvt;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class LinearVelocityEvent extends B2DBaseEvent<LinearVelocityEvent> {

    public Vector2 velocity = new Vector2();

    public LinearVelocityEvent() {
        super(B2DEventsEnum.LinearVelocity);
    }

    @Override
    public boolean canConcat(LinearVelocityEvent event) {
        this.velocity.add(event.velocity);
        return true;
    }

    @Override
    public void doReset() {
        this.velocity.setZero();
    }

    @Override
    public void apply(Body body) {
        body.setLinearVelocity(velocity);
    }

}
