package com.nzt.b2d.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;

public class ActiveBodyEvent extends B2DBaseEvent<ActiveBodyEvent> {

    public boolean active;

    public ActiveBodyEvent() {
        super(B2DEventsEnum.Active);
    }

    @Override
    public boolean canConcat(ActiveBodyEvent event) {
        return true;
    }

    @Override
    public void doReset() {
        active = false;
    }

    @Override
    public void apply(Body body) {
        body.setActive(active);
    }

}
