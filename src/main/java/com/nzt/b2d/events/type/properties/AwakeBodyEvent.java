package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    protected void concat(AwakeBodyEvent event) {
        this.awake = event.awake;
    }

    @Override
    public void reset() {
        awake = false;
    }

    @Override
    public void apply(Body body) {
        body.setAwake(awake);
    }

}
