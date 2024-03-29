package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.MassData;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MassDataChangeEvent extends B2dEvent<MassDataChangeEvent> {

    private MassData massData;

    public MassDataChangeEvent() {
        super(B2dEventsEnum.MassData);
    }

    @Override
    protected boolean canConcat(MassDataChangeEvent event) {
        return true;
    }

    @Override
    protected void concat(MassDataChangeEvent event) {
        this.setMassData(event.getMassData());
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setMassData(massData);
    }

    @Override
    public void reset() {

    }
}
