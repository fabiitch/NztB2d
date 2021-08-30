package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;

public class BodyTypeEvent extends B2dBaseEvent<BodyTypeEvent> {

    public BodyType bodyType;

    public BodyTypeEvent() {
        super(B2dEventsEnum.BodyType);
    }

    @Override
    public boolean canConcat(BodyTypeEvent event) {
        return true;
    }

    @Override
    public void doReset() {
        bodyType = null;
    }

    @Override
    public void apply(Body body) {
        body.setType(bodyType);
    }

}
