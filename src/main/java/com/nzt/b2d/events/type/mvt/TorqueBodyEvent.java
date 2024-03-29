package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorqueBodyEvent extends B2dBaseEvent<TorqueBodyEvent> {

    private float torque;
    private boolean wake;

    private boolean setValue;

    public TorqueBodyEvent() {
        super(B2dEventsEnum.Torque);
    }

    @Override
    public boolean canConcat(TorqueBodyEvent event) {
        this.torque = event.torque;

        return true;
    }

    @Override
    protected void concat(TorqueBodyEvent event) {
        if (event.setValue) {
            this.torque = event.getTorque();
        } else {
            this.torque += event.getTorque();
        }
        this.wake = event.wake;
    }

    @Override
    public void apply(Body body) {
        body.applyTorque(torque, wake);
    }

    @Override
    public void reset() {
        this.torque = 0f;
        this.wake = false;
    }
}
