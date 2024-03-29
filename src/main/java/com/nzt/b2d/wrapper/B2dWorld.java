package com.nzt.b2d.wrapper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dEventContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2dWorld {
    @Getter
    private World world;

    @Getter
    private final Array<B2dBody> bodies = new Array<>();

    @Getter
    @Setter
    private float stepTime = 1;
    private int velocityIteration = 1, positionIteration = 1;

    private float stepTimeAccumulator = 0f;
    private int idGenerator = 1;

    public B2dWorld() {
        world = new World(new Vector2(), true);
    }


    public void step(float dt) {
        stepTimeAccumulator += dt;

        while (stepTimeAccumulator >= stepTime) {
            world.step(stepTime, velocityIteration, positionIteration);
            processEvents();
            stepTimeAccumulator -= stepTime;
        }
    }

    private void processEvents() {
        for (int i = 0, n = bodies.size; i < n; i++) {
            B2dBody b2dBody = bodies.get(i);
            B2dEventContainer eventContainer = b2dBody.getEventContainer();
            eventContainer.process(b2dBody);
        }
    }

    public B2dBody createBody(BodyDef bodyDef) {
        Body body = world.createBody(bodyDef);
        B2dBody b2dBody = new B2dBody(idGenerator++, body, this);
        bodies.add(b2dBody);
        return b2dBody;
    }



    public void destroyBody(B2dBody b2dBody) {
        world.destroyBody(b2dBody.getBody());
        b2dBody.setDestroyed(true);
    }
}
