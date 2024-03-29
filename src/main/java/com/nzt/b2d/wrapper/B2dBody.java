package com.nzt.b2d.wrapper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dEventContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2dBody {

    private int id;
    private Body body;
    private boolean destroyed;

    private final B2dEventContainer eventContainer = new B2dEventContainer();
    private Array<B2dFixture> fixtures = new Array<>();

    private B2dWorld b2dWorld;

    public B2dBody(int id, Body body, B2dWorld b2dWorld) {
        this.id = id;
        this.body = body;
        this.b2dWorld = b2dWorld;
    }


    public void createFixture(FixtureDef fixtureDef) {
        Fixture fixture = body.createFixture(fixtureDef);
    }
    public void destroyFixture(B2dFixture fixtureToDestroy) {
        B2dFixture b2dFixture = fixtures.get(1);
        b2dFixture.setDestroyed(true);
        body.destroyFixture(b2dFixture.getFixture());

        eventContainer.removeEventsForFixture(fixtureToDestroy);
    }

}
