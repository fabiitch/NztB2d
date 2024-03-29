package com.nzt.b2d.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pools;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.events.type.fixture.impl.*;
import com.nzt.b2d.events.type.properties.AddFixtureEvent;
import com.nzt.b2d.wrapper.B2dFixture;

public class B2dFixtureEventFactory {

    private B2dFixtureEventFactory() {

    }

    private static <E extends B2dEvent> E getEvent(B2dEventsEnum eventType) {
        B2dEvent event;
        switch (eventType) {
            case FixtureAdd:
                event = Pools.obtain(AddFixtureEvent.class);
                break;
            case FixtureDestroy:
                event = Pools.obtain(DestroyFixtureEvent.class);
                break;
            case FixtureDefChange:
                event = Pools.obtain(ChangeFixtureDefEvent.class);
                break;
            case FixtureSensor:
                event = Pools.obtain(SensorFixtureEvent.class);
                break;
            case FixtureFilter:
                event = Pools.obtain(FilterDataFixtureEvent.class);
                break;
            default:
                event = null;
                Gdx.app.error("B2DFixtureEventFactory", eventType + "not impl");
                break;
        }
        return (E) event;
    }

    public static AddFixtureEvent add(FixtureDef fixtureDef) {
        AddFixtureEvent event = getEvent(B2dEventsEnum.FixtureAdd);
        event.setFixtureDef(fixtureDef);
        return event;
    }

    public static DestroyFixtureEvent destroy(B2dFixture b2dFixture) {
        DestroyFixtureEvent event = getEvent(B2dEventsEnum.FixtureDestroy);
        event.setB2dFixture(b2dFixture);
        return event;
    }

    public static ChangeFixtureDefEvent fixtureDefinition(B2dFixture b2dFixture, FixtureDef fixtureDef) {
        ChangeFixtureDefEvent event = getEvent(B2dEventsEnum.FixtureDefChange);
        event.setB2dFixture(b2dFixture);
        event.setFixtureDef(fixtureDef);
        return event;
    }

    /**
     * Sensor=true = no collision response
     */
    public static SensorFixtureEvent sensor(B2dFixture b2dFixture, boolean sensor) {
        SensorFixtureEvent event = getEvent(B2dEventsEnum.FixtureSensor);
        event.setB2dFixture(b2dFixture);
        event.setSensor(sensor);
        return event;
    }

    public static FilterDataFixtureEvent fixtureFilter(B2dFixture b2dFixture, Filter filter) {
        FilterDataFixtureEvent event = getEvent(B2dEventsEnum.FixtureFilter);
        event.setB2dFixture(b2dFixture);
        event.setFilter(filter);
        return event;
    }

}
