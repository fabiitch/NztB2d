package com.nzt.b2d.events.type.fixture;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseApplyToFixtureEvent<E extends BaseApplyToFixtureEvent> extends BaseFixtureBodyEvent<E> {
    private int fixtureNumber = -1;//-1 == to applyToAll

    public BaseApplyToFixtureEvent(B2dFixtureEventsEnum fixtureEnum) {
        super(fixtureEnum);
    }

    public void setFixtureNumber(int fixtureNumber) {
        this.fixtureNumber = fixtureNumber;
    }

    public boolean applyToAll() {
        return fixtureNumber < 0;
    }

    @Override
    protected boolean canConcat(E event) {
        return this.fixtureNumber == event.getFixtureNumber();
    }

    @Override
    public void reset() {
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
