package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseApplyToFixtureEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDataFixtureEvent extends BaseApplyToFixtureEvent<FilterDataFixtureEvent> {

    private Filter filter;

    public FilterDataFixtureEvent() {
        super(B2dFixtureEventsEnum.Filter);
    }

    @Override
    protected boolean canConcat(FilterDataFixtureEvent event) {
        return false;
    }

    @Override
    protected void concat(FilterDataFixtureEvent event) {

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
