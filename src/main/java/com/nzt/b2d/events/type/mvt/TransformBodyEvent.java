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
public class TransformBodyEvent extends B2dEvent<TransformBodyEvent> {

    public final Vector2 positionTo = new Vector2();
    public float rotation;

    public TransformBodyEvent() {
        super(B2dEventsEnum.Transform);
    }


    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setTransform(positionTo, rotation);
    }

    @Override
    public boolean canConcat(TransformBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(TransformBodyEvent event) {
        this.positionTo.set(event.positionTo);
        this.rotation = event.rotation;
    }

    @Override
    public void reset() {
        this.positionTo.setZero();
        this.rotation = 0;
    }

}
