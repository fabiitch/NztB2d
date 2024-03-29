package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedRotationBodyEvent extends B2dEvent<FixedRotationBodyEvent> {

    private boolean fixedRotation;

    public FixedRotationBodyEvent() {
        super(B2dEventsEnum.AngularDamping);
    }

    @Override
    public boolean canConcat(FixedRotationBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(FixedRotationBodyEvent event) {
        this.fixedRotation = event.isFixedRotation();
    }

    @Override
    public void reset() {
        fixedRotation = false;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setFixedRotation(fixedRotation);
    }

}
