package com.nzt.gdx.b2d.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pools;
import com.nzt.gdx.b2d.events.B2DBaseEvent;
import com.nzt.gdx.b2d.events.B2DFixtureEventsEnum;
import com.nzt.gdx.b2d.events.type.fixture.BaseFixtureBodyEvent;
import com.nzt.gdx.b2d.events.type.fixture.impl.*;

public class B2DFixtureEventFactory {

    private B2DFixtureEventFactory() {

    }

    private static <E extends BaseFixtureBodyEvent> E getEvent(B2DFixtureEventsEnum eventType) {
        B2DBaseEvent event;
        switch (eventType) {
            case Add:
                event = Pools.obtain(AddFixtureEvent.class);
                break;
            case Destroy:
                event = Pools.obtain(DestroyFixtureEvent.class);
                break;
            case Change:
                event = Pools.obtain(ChangeFixtureEvent.class);
                break;
            case Sensor:
                event = Pools.obtain(SensorFixtureEvent.class);
                break;
            case Filter:
                event = Pools.obtain(FilterFixtureEvent.class);
                break;
            case UserData:
                event = Pools.obtain(UserDataFixtureEvent.class);
                break;
            default:
                event = null;
                Gdx.app.error("B2DFixtureEventFactory", eventType + "not impl");
                break;
        }
        return (E) event;
    }

    public static AddFixtureEvent add(FixtureDef fixtureDef) {
        AddFixtureEvent event = getEvent(B2DFixtureEventsEnum.Add);
        event.fixtureDef = fixtureDef;
        return event;
    }

    public static DestroyFixtureEvent destroy(int fixtureNumber) {
        DestroyFixtureEvent event = getEvent(B2DFixtureEventsEnum.Destroy);
        event.setFixtureNumber(fixtureNumber);
        return event;
    }

    public static ChangeFixtureEvent change(int fixtureNumber, FixtureDef fixtureDef) {
        ChangeFixtureEvent event = getEvent(B2DFixtureEventsEnum.Change);
        event.setFixtureNumber(fixtureNumber);
        event.fixtureDef = fixtureDef;
        return event;
    }

    /**
     * Sensor=true = no collision response
     */
    public static SensorFixtureEvent sensor(int fixtureNumber, boolean sensor) {
        SensorFixtureEvent event = getEvent(B2DFixtureEventsEnum.Sensor);
        event.setFixtureNumber(fixtureNumber);
        event.sensor = sensor;
        return event;
    }

    public static FilterFixtureEvent fixtureFilter(int fixtureNumber, Filter filter) {
        FilterFixtureEvent event = getEvent(B2DFixtureEventsEnum.Filter);
        event.setFixtureNumber(fixtureNumber);
        event.filter = filter;
        return event;
    }

    public static UserDataFixtureEvent userData(int fixtureNumber, Object userData) {
        UserDataFixtureEvent event = getEvent(B2DFixtureEventsEnum.Filter);
        event.setFixtureNumber(fixtureNumber);
        event.userdata = userData;
        return event;
    }
}
