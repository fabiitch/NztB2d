package com.nzt.gdx.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.b2d.events.B2dBaseEvent;
import com.nzt.gdx.b2d.events.B2dEventsEnum;

public class ActiveBodyEvent extends B2dBaseEvent<ActiveBodyEvent> {

    public boolean active;

    public ActiveBodyEvent() {
        super(B2dEventsEnum.Active);
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
