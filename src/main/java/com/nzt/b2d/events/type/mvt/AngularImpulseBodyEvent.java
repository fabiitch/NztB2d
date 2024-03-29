package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AngularImpulseBodyEvent extends B2dEvent<AngularImpulseBodyEvent> {

    private float impulse;
    private boolean wake;
    private boolean setValue;

    public AngularImpulseBodyEvent() {
        super(B2dEventsEnum.AngularImpulse);
    }

    @Override
    public boolean canConcat(AngularImpulseBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(AngularImpulseBodyEvent event) {
        if (setValue) {
            this.impulse = event.impulse;
            this.wake = event.wake;
        } else {
            this.impulse += event.impulse;
            this.wake = event.wake;
        }
    }

    @Override
    public void reset() {
        this.impulse = 0f;
        this.wake = false;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().applyAngularImpulse(impulse, wake);
    }

}
