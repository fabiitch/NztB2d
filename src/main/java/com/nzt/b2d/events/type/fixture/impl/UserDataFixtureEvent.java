package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;

public class UserDataFixtureEvent extends BaseApplyToFixtureEvent<UserDataFixtureEvent> {

    public Object userdata;

    public UserDataFixtureEvent(B2dFixtureEventsEnum fixtureEnum) {
        super(B2dFixtureEventsEnum.UserData);
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
