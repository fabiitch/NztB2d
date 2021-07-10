package com.nzt.b2d.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;
import com.nzt.gdx.b2d.events.type.fixture.BaseApplyToFixtureEvent;

public class FilterFixtureEvent extends BaseApplyToFixtureEvent<FilterFixtureEvent> {

    public Filter filter;

    public FilterFixtureEvent() {
        super(B2DFixtureEventsEnum.Filter);
    }

    @Override
    protected boolean canConcat(FilterFixtureEvent event) {
        return false;
    }

    @Override
    protected void resetFixtureEvent() {
        filter = null;
    }

    @Override
    public void applyOnFixture(Fixture fixture) {
        fixture.setFilterData(filter);
    }
}
