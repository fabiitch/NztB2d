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
public class ApplyForceCenterBodyEvent extends B2dEvent<ApplyForceCenterBodyEvent> {

    private final Vector2 force = new Vector2();
    private boolean wake;
    private boolean setValue;

    public ApplyForceCenterBodyEvent() {
        super(B2dEventsEnum.ApplyForceToCenter);
    }

    @Override
    public boolean canConcat(ApplyForceCenterBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(ApplyForceCenterBodyEvent event) {
        if (setValue) {
            this.force.set(event.force);
        } else {
            this.force.add(event.force);
        }
        this.wake = event.wake;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().applyForceToCenter(force, wake);
    }

    @Override
    public void reset() {
        this.force.setZero();
        this.wake = false;
    }


}
