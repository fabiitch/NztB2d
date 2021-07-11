package com.nzt.gdx.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class TorqueBodyEvent extends B2DBaseEvent<TorqueBodyEvent> {

    public float torque;
    public boolean wake;

    public TorqueBodyEvent() {
        super(B2DEventsEnum.Torque);
    }

    @Override
    public boolean canConcat(TorqueBodyEvent event) {
        this.torque = event.torque;
        this.wake = event.wake;
        return true;
    }

    @Override
    public void doReset() {
        this.torque = 0f;
        this.wake = false;
    }

    @Override
    public void apply(Body body) {
        body.applyTorque(torque, wake);
    }

}
