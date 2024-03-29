package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotationBodyEvent extends B2dEvent<RotationBodyEvent> {

    private float angleRadian;
    public RotationBodyEvent() {
        super(B2dEventsEnum.Rotation);
    }


    @Override
    public void apply(B2dBody b2dBody) {
        Body body = b2dBody.getBody();
        body.setTransform(body.getPosition(), angleRadian);
    }

    @Override
    public boolean canConcat(RotationBodyEvent event) {
        return false;
    }

    @Override
    protected void concat(RotationBodyEvent event) {

    }

    @Override
    public void reset() {
        this.angleRadian = 0;
    }
}
