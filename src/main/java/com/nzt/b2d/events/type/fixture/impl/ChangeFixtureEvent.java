package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;

public class ChangeFixtureEvent extends BaseApplyToFixtureEvent<ChangeFixtureEvent> {

    public FixtureDef fixtureDef;

    public ChangeFixtureEvent() {
        super(B2dFixtureEventsEnum.Change);
    }

    @Override
    protected boolean canConcat(ChangeFixtureEvent event) {
        return false;
    }


    @Override
    protected void resetFixtureEvent() {

    }

    @Override
    public void applyOnFixture(Fixture fixture) {
        fixture.setDensity(fixtureDef.density);
        fixture.setFilterData(fixtureDef.filter);
        fixture.setFriction(fixtureDef.friction);
        fixture.setRestitution(fixtureDef.restitution);
        fixture.setSensor(fixtureDef.isSensor);
    }

}
