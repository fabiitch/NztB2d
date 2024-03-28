package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    protected void concat(BodyTypeEvent event) {
        this.bodyType = event.getBodyType();
    }

    @Override
    public void reset() {
        bodyType = null;
    }

    @Override
    public void apply(Body body) {
        body.setType(bodyType);
    }

}
