package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestroyFixtureEvent extends BaseApplyToFixtureEvent<DestroyFixtureEvent> {

    public DestroyFixtureEvent() {
        super(B2dFixtureEventsEnum.Destroy);
    }

    @Override
    protected boolean canConcat(DestroyFixtureEvent event) {
        return event.getFixtureNumber() == this.getFixtureNumber();
    }

    @Override
    protected void concat(DestroyFixtureEvent event) {

    }

    @Override
    protected void resetFixtureEvent() {

    }

    public void apply(Body body) {
        Array<Fixture> fixtureList = body.getFixtureList();
        if (applyToAll()) {
            for (Fixture fixture : fixtureList) {
                body.destroyFixture(fixture);
            }
        } else {
            body.destroyFixture(fixtureList.get(getFixtureNumber()));
        }
    }

    @Override
    public void applyOnFixture(Fixture fixture) {

    }

}
