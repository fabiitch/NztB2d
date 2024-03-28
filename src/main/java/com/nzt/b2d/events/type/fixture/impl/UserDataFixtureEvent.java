package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataFixtureEvent extends BaseApplyToFixtureEvent<UserDataFixtureEvent> {
    private Object userdata;

    public UserDataFixtureEvent(B2dFixtureEventsEnum fixtureEnum) {
        super(B2dFixtureEventsEnum.UserData);
    }

    @Override
    protected void concat(UserDataFixtureEvent event) {

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
