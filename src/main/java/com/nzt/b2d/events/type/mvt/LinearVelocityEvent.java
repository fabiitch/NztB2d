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
public class LinearVelocityEvent extends B2dEvent<LinearVelocityEvent> {
    private final Vector2 velocity = new Vector2();

    public LinearVelocityEvent() {
        super(B2dEventsEnum.LinearVelocity);
    }

    @Override
    public boolean canConcat(LinearVelocityEvent event) {
        return true;
    }

    @Override
    protected void concat(LinearVelocityEvent event) {
        this.velocity.set(event.velocity);
    }

    @Override
    public void reset() {
        this.velocity.setZero();
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setLinearVelocity(velocity);
    }

}
