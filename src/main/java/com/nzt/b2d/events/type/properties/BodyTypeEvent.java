package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyTypeEvent extends B2dEvent<BodyTypeEvent> {

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
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setType(bodyType);
    }

}
