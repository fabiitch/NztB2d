package com.nzt.b2d.events.type.fixture;

import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.B2dFixtureEventsEnum;

public abstract class BaseFixtureBodyEvent<E extends BaseFixtureBodyEvent> extends B2dBaseEvent<E> {


    public BaseFixtureBodyEvent(B2dFixtureEventsEnum fixtureEnum) {
        super((short) (B2dEventsEnum.Fixture.ordinal() + fixtureEnum.ordinal()));
    }
}
