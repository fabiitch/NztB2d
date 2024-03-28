package com.nzt.b2d.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Pools;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dFixtureEventsEnum;
import com.nzt.b2d.events.type.fixture.BaseFixtureBodyEvent;
import com.nzt.b2d.events.type.fixture.impl.*;

public class B2dFixtureEventFactory {

    private B2dFixtureEventFactory() {

    }

    private static <E extends BaseFixtureBodyEvent> E getEvent(B2dFixtureEventsEnum eventType) {
        B2dBaseEvent event;
        switch (eventType) {
            case Add:
                event = Pools.obtain(AddFixtureEvent.class);
                break;
            case Destroy:
                event = Pools.obtain(DestroyFixtureEvent.class);
                break;
            case Change:
                event = Pools.obtain(ChangeFixtureDefEvent.class);
                break;
            case Sensor:
                event = Pools.obtain(SensorFixtureEvent.class);
                break;
            case Filter:
                event = Pools.obtain(FilterDataFixtureEvent.class);
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
        AddFixtureEvent event = getEvent(B2dFixtureEventsEnum.Add);
        event.setFixtureDef(fixtureDef);
        return event;
    }

    public static DestroyFixtureEvent destroy(int fixtureNumber) {
        DestroyFixtureEvent event = getEvent(B2dFixtureEventsEnum.Destroy);
        event.setFixtureNumber(fixtureNumber);
        return event;
    }

    public static ChangeFixtureDefEvent change(int fixtureNumber, FixtureDef fixtureDef) {
        ChangeFixtureDefEvent event = getEvent(B2dFixtureEventsEnum.Change);
        event.setFixtureNumber(fixtureNumber);
        event.setFixtureDef(fixtureDef);
        return event;
    }

    /**
     * Sensor=true = no collision response
     */
    public static SensorFixtureEvent sensor(int fixtureNumber, boolean sensor) {
        SensorFixtureEvent event = getEvent(B2dFixtureEventsEnum.Sensor);
        event.setFixtureNumber(fixtureNumber);
        event.setSensor(sensor);
        return event;
    }

    public static FilterDataFixtureEvent fixtureFilter(int fixtureNumber, Filter filter) {
        FilterDataFixtureEvent event = getEvent(B2dFixtureEventsEnum.Filter);
        event.setFixtureNumber(fixtureNumber);
        event.setFilter(filter);
        return event;
    }

    public static UserDataFixtureEvent userData(int fixtureNumber, Object userData) {
        UserDataFixtureEvent event = getEvent(B2dFixtureEventsEnum.Filter);
        event.setFixtureNumber(fixtureNumber);
        event.setUserdata(userData);
        return event;
    }
}
