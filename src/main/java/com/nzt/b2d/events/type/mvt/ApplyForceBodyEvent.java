package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyForceBodyEvent extends B2dEvent<ApplyForceBodyEvent> {

    public Vector2 force;
    public Vector2 point;
    public boolean wake;
    private boolean setValue;
    public ApplyForceBodyEvent() {
        super(B2dEventsEnum.ApplyForce);
    }

    @Override
    public boolean canConcat(ApplyForceBodyEvent event) {
        return false;
    }

    @Override
    protected void concat(ApplyForceBodyEvent event) {

    }

    @Override
    public void reset() {
        this.force.setZero();
        this.point.setZero();
        this.wake = false;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().applyForce(force, point, wake);
    }

}
