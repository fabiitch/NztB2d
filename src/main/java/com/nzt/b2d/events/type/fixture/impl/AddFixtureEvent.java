package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseFixtureBodyEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFixtureEvent extends BaseFixtureBodyEvent<AddFixtureEvent> {

    private FixtureDef fixtureDef;

    public AddFixtureEvent() {
        super(B2dFixtureEventsEnum.Add);
    }

    @Override
    protected boolean canConcat(AddFixtureEvent event) {
        return false;
    }

    @Override
    protected void concat(AddFixtureEvent event) {

    }

    @Override
    public void apply(Body body) {
        body.createFixture(fixtureDef);
    }

    @Override
    public void reset() {
        fixtureDef = null;
    }

}
