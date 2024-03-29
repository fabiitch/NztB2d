package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.type.fixture.B2dFixtureEvent;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorFixtureEvent extends B2dFixtureEvent<SensorFixtureEvent> {

    private boolean sensor;

    public SensorFixtureEvent() {
        super(B2dEventsEnum.FixtureSensor);
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
    public void applyOnFixture(B2dFixture b2dFixture) {
        b2dFixture.getFixture().setSensor(sensor);
    }


}
