package com.nzt.b2d.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;
import com.nzt.gdx.b2d.events.type.fixture.BaseApplyToFixtureEvent;

public class UserDataFixtureEvent extends BaseApplyToFixtureEvent<UserDataFixtureEvent> {

    public Object userdata;

    public UserDataFixtureEvent(B2DFixtureEventsEnum fixtureEnum) {
        super(B2DFixtureEventsEnum.UserData);
    }

    @Override
    protected boolean canConcat(UserDataFixtureEvent event) {
        return true;
    }


    @Override
    protected void resetFixtureEvent() {
        userdata = null;
    }

    @Override
    public void applyOnFixture(Fixture fixture) {
        fixture.setUserData(userdata);
    }

}
