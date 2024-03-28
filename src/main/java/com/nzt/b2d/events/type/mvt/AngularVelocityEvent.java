package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    public void reset() {
        this.angularVelocity = 0f;
    }

    @Override
    public void apply(Body body) {
        body.setAngularVelocity(angularVelocity);
    }

}
