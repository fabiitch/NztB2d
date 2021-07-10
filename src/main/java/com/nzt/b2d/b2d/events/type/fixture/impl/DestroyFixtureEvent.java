package com.nzt.b2d.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;
import com.nzt.gdx.b2d.events.type.fixture.BaseApplyToFixtureEvent;

public class DestroyFixtureEvent extends BaseApplyToFixtureEvent<DestroyFixtureEvent> {

    public DestroyFixtureEvent() {
        super(B2DFixtureEventsEnum.Destroy);
    }

    @Override
    protected boolean canConcat(DestroyFixtureEvent event) {
        return false;
    }

    @Override
    protected void resetFixtureEvent() {

    }

    public void apply(Body body) {
        Array<Fixture> fixtureList = body.getFixtureList();
        if (applyToAll()) {
            for (Fixture fixture : fixtureList) {
                body.destroyFixture(fixture);
            }
        } else {
            body.destroyFixture(fixtureList.get(fixtureNumber));
        }
    }

    @Override
    public void applyOnFixture(Fixture fixture) {

    }

}
