package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.type.fixture.B2dFixtureEvent;
import com.nzt.b2d.wrapper.B2dBody;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestroyFixtureEvent extends B2dFixtureEvent<DestroyFixtureEvent> {

    public DestroyFixtureEvent() {
        super(B2dEventsEnum.FixtureDestroy);
    }


    @Override
    protected void concat(DestroyFixtureEvent event) {

    }

    @Override
    protected void resetFixtureEvent() {

    }

    @Override
    public void applyOnFixture(B2dFixture b2dFixture) {
        b2dFixture.getB2dBody().destroyFixture(b2dFixture);
    }
}
