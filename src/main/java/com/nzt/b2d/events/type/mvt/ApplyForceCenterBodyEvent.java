package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyForceCenterBodyEvent extends B2dBaseEvent<ApplyForceCenterBodyEvent> {

    public Vector2 force;
    public boolean wake;

    public ApplyForceCenterBodyEvent() {
        super(B2dEventsEnum.ApplyForceToCenter);
    }

    @Override
    public boolean canConcat(ApplyForceCenterBodyEvent event) {
        return false;
    }

    @Override
    public void reset() {
        this.force.setZero();
        this.wake = false;
    }

    @Override
    public void apply(Body body) {
        body.applyForceToCenter(force, wake);

    }

}
