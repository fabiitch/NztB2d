package com.nzt.b2d.events.type.properties;

import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GravityScaleEvent extends B2dEvent<GravityScaleEvent> {

    private float gravityScale;

    public GravityScaleEvent() {
        super(B2dEventsEnum.GravityScale);
    }

    @Override
    protected boolean canConcat(GravityScaleEvent event) {
        return true;
    }

    @Override
    protected void concat(GravityScaleEvent event) {
        this.gravityScale = event.getGravityScale();
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setGravityScale(gravityScale);
    }

    @Override
    public void reset() {

    }
}
