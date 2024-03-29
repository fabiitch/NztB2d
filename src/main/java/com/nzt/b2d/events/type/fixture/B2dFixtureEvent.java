package com.nzt.b2d.events.type.fixture;

import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class B2dFixtureEvent<E extends B2dFixtureEvent> extends B2dEvent<E> {

    private B2dFixture b2dFixture;

    public B2dFixtureEvent(B2dEventsEnum fixtureEnum) {
        super(fixtureEnum);
    }


    public boolean applyToAll() {
        return b2dFixture != null;
    }

    @Override
    protected boolean canConcat(E event) {
        return isOnSameFixture(event);
    }

    public boolean isOnSameFixture(E event) {
        return this.b2dFixture == event.getB2dFixture();
    }

    @Override
    public void reset() {
        b2dFixture = null;
        resetFixtureEvent();
    }

    protected abstract void resetFixtureEvent();

    @Override
    public void apply(B2dBody b2dBody) {
        Array<B2dFixture> fixtures = b2dBody.getFixtures();
        if (applyToAll()) {
            for (B2dFixture fixture : fixtures) {
                applyOnFixture(fixture);
            }
        } else {
            applyOnFixture(b2dFixture);
        }
    }

    public abstract void applyOnFixture(B2dFixture b2dFixture);
}
