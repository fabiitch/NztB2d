package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    protected void concat(ActiveBodyEvent event) {
        this.active = event.active;
    }

    @Override
    public void reset() {
        active = false;
    }

    @Override
    public void apply(Body body) {
        body.setActive(active);
    }

}
