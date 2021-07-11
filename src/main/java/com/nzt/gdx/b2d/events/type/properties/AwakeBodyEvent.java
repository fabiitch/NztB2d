package com.nzt.gdx.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class AwakeBodyEvent extends B2DBaseEvent<AwakeBodyEvent> {

    public boolean awake;

    public AwakeBodyEvent() {
        super(B2DEventsEnum.Awake);
    }

    @Override
    protected boolean canConcat(AwakeBodyEvent event) {
        return true;
    }

    @Override
    public void doReset() {
        awake = false;
    }

    @Override
    public void apply(Body body) {
        body.setAwake(awake);
    }

}
