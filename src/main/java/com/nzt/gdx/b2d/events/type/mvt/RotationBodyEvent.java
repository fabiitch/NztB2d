package com.nzt.gdx.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2dBaseEvent;
import com.nzt.gdx.b2d.events.B2dEventsEnum;

public class RotationBodyEvent extends B2dBaseEvent<RotationBodyEvent> {

    public float angleRadian;
    public short priority;

    public RotationBodyEvent() {
        super(B2dEventsEnum.Rotation);
    }


    @Override
    public void apply(Body body) {
        body.setTransform(body.getPosition(), angleRadian);
    }

    @Override
    public boolean canConcat(RotationBodyEvent event) {
        if (this.priority < event.priority) {
            this.angleRadian = event.angleRadian;
            this.priority = event.priority;
        }
        return true;
    }

    @Override
    public void doReset() {
        this.angleRadian = 0;
        this.priority = 0;
    }
}
