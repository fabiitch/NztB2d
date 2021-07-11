package com.nzt.gdx.b2d.events.type.fixture;

import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DEventsEnum;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;

public abstract class BaseFixtureBodyEvent<E extends BaseFixtureBodyEvent> extends B2DBaseEvent<E> {


    public BaseFixtureBodyEvent(B2DFixtureEventsEnum fixtureEnum) {
        super((short) (B2DEventsEnum.Fixture.ordinal() + fixtureEnum.ordinal()));
    }
}
