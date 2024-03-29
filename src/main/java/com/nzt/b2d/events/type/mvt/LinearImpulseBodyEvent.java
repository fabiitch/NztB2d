package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinearImpulseBodyEvent extends B2dBaseEvent<LinearImpulseBodyEvent> {

    private final Vector2 impulse = new Vector2();
    private final Vector2 point =  new Vector2();
    public boolean wake;

    private boolean setValue;
    public LinearImpulseBodyEvent() {
        super(B2dEventsEnum.LinearImpulse);
    }

    @Override
    public boolean canConcat(LinearImpulseBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(LinearImpulseBodyEvent event) {
        if(setValue){
            impulse.set(event.impulse);
            point.set(event.point);
        }else{
            impulse.add(event.impulse);
            point.add(event.point);
        }
    }

    @Override
    public void reset() {
        this.impulse.setZero();
        this.point.setZero();
        this.wake = false;
    }

    @Override
    public void apply(Body body) {
        body.applyLinearImpulse(impulse, point, wake);
    }

}
