package com.nzt.gdx.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class BodyTypeEvent extends B2DBaseEvent<BodyTypeEvent> {

    public BodyType bodyType;

    public BodyTypeEvent() {
        super(B2DEventsEnum.BodyType);
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
