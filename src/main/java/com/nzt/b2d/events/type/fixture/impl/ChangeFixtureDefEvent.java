package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.type.fixture.B2dFixtureEvent;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeFixtureDefEvent extends B2dFixtureEvent<ChangeFixtureDefEvent> {

    private FixtureDef fixtureDef;

    public ChangeFixtureDefEvent() {
        super(B2dEventsEnum.FixtureDefChange);
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
    public void applyOnFixture(B2dFixture b2dFixture) {
        Fixture fixture = b2dFixture.getFixture();
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
