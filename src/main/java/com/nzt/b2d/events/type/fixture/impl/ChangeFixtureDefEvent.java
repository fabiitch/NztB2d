package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeFixtureDefEvent extends BaseApplyToFixtureEvent<ChangeFixtureDefEvent> {

    private FixtureDef fixtureDef;

    public ChangeFixtureDefEvent() {
        super(B2dFixtureEventsEnum.Change);
    }

    @Override
    protected boolean canConcat(ChangeFixtureDefEvent event) {
        return true;
    }

    @Override
    protected void concat(ChangeFixtureDefEvent event) {
        this.fixtureDef = event.getFixtureDef();
    }

    @Override
    public void applyOnFixture(Fixture fixture) {
        fixture.setDensity(fixtureDef.density);
        fixture.setFilterData(fixtureDef.filter);
        fixture.setFriction(fixtureDef.friction);
        fixture.setRestitution(fixtureDef.restitution);
        fixture.setSensor(fixtureDef.isSensor);
    }
    @Override
    protected void resetFixtureEvent() {
    }
}
