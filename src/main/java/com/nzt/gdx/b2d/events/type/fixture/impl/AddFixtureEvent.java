package com.nzt.gdx.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.gdx.b2d.events.B2dFixtureEventsEnum;
import com.nzt.gdx.b2d.events.type.fixture.BaseFixtureBodyEvent;

public class AddFixtureEvent extends BaseFixtureBodyEvent<AddFixtureEvent> {

    public FixtureDef fixtureDef;

    public AddFixtureEvent() {
        super(B2dFixtureEventsEnum.Add);
    }

    @Override
    protected boolean canConcat(AddFixtureEvent event) {
        return false;
    }

    @Override
    protected void doReset() {
        fixtureDef = null;
    }

    @Override
    public void apply(Body body) {
        body.createFixture(fixtureDef);
    }
}
