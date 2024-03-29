package com.nzt.b2d.events.type.mvt;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AngularDampingBodyEvent extends B2dEvent<AngularDampingBodyEvent> {

    private float angularDamping;
    private boolean setValue;

    public AngularDampingBodyEvent() {
        super(B2dEventsEnum.AngularDamping);
    }

    @Override
    public boolean canConcat(AngularDampingBodyEvent event) {
        this.angularDamping = event.angularDamping;
        return true;
    }
    @Override
    protected void concat(AngularDampingBodyEvent event) {
        if (event.setValue) {
            angularDamping = event.angularDamping;
        } else {
            angularDamping +=event.angularDamping;
        }
    }

    @Override
    public void reset() {
        this.angularDamping = 0f;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setAngularDamping(angularDamping);
    }

}
