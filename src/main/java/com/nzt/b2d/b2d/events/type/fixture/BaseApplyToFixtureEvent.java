package com.nzt.b2d.b2d.events.type.fixture;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;

public abstract class BaseApplyToFixtureEvent<E extends BaseFixtureBodyEvent> extends BaseFixtureBodyEvent<E> {
    public int fixtureNumber = -1;//-1 == to applyToAll

    public BaseApplyToFixtureEvent(B2DFixtureEventsEnum fixtureEnum) {
        super(fixtureEnum);
    }

    public void setFixtureNumber(int fixtureNumber) {
        this.fixtureNumber = fixtureNumber;
    }

    public boolean applyToAll() {
        return fixtureNumber < 0;
    }

    @Override
    protected final void doReset() {
        fixtureNumber = -1;
        resetFixtureEvent();
    }

    protected abstract void resetFixtureEvent();

    @Override
    public void apply(Body body) {
        Array<Fixture> fixtureList = body.getFixtureList();
        if (applyToAll()) {
            for (Fixture fixture : fixtureList) {
                applyOnFixture(fixture);
            }
        } else {
            applyOnFixture(fixtureList.get(fixtureNumber));
        }
    }

    public abstract void applyOnFixture(Fixture fixture);
}
