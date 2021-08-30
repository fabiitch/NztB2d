package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;

public class AwakeBodyEvent extends B2dBaseEvent<AwakeBodyEvent> {

    public boolean awake;

    public AwakeBodyEvent() {
        super(B2dEventsEnum.Awake);
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
