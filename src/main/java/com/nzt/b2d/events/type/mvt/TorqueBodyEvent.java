package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TorqueBodyEvent extends B2dEvent<TorqueBodyEvent> {

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
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().applyTorque(torque, wake);
    }

    @Override
    public void reset() {
        this.torque = 0f;
        this.wake = false;
    }
}
