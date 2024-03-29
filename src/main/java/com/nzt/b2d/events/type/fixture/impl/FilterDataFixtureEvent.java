package com.nzt.b2d.events.type.fixture.impl;

import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.type.fixture.B2dFixtureEvent;
import com.nzt.b2d.wrapper.B2dFixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDataFixtureEvent extends B2dFixtureEvent<FilterDataFixtureEvent> {

    private Filter filter;

    public FilterDataFixtureEvent() {
        super(B2dEventsEnum.FixtureFilter);
    }

    @Override
    protected boolean canConcat(FilterDataFixtureEvent event) {
        return true;
    }

    @Override
    protected void concat(FilterDataFixtureEvent event) {
        filter = event.filter;
    }

    @Override
    protected void resetFixtureEvent() {
        filter = null;
    }

    @Override
    public void applyOnFixture(B2dFixture b2dFixture) {
        b2dFixture.getFixture().setFilterData(filter);
    }

}
