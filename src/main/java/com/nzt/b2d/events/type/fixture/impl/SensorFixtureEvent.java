package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorFixtureEvent extends BaseApplyToFixtureEvent<SensorFixtureEvent> {

    private boolean sensor;

    public SensorFixtureEvent() {
        super(B2dFixtureEventsEnum.Sensor);
    }

    @Override
    protected boolean canConcat(SensorFixtureEvent event) {
        return true;
    }

    @Override
    protected void concat(SensorFixtureEvent event) {
        sensor = event.isSensor();
    }


    @Override
    protected void resetFixtureEvent() {
        sensor = false;
    }

    @Override
    public void applyOnFixture(Fixture fixture) {
        fixture.setSensor(sensor);
    }

}
