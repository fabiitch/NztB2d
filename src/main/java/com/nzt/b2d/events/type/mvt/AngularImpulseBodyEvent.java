package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AngularImpulseBodyEvent extends B2dBaseEvent<AngularImpulseBodyEvent> {

    public float impulse;
    public boolean wake;
    public boolean set;

    public AngularImpulseBodyEvent() {
        super(B2dEventsEnum.AngularImpulse);
    }

    @Override
    public boolean canConcat(AngularImpulseBodyEvent event) {
        this.impulse = event.impulse;
        this.wake = event.wake;
        return true;
    }

    @Override
    public void reset() {
        this.impulse = 0f;
        this.wake = false;
    }

    @Override
    public void apply(Body body) {
        body.applyAngularImpulse(impulse, wake);
    }

}
