package com.nzt.b2d.wrapper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventContainer;
import com.nzt.b2d.events.B2dEventsEnum;

public class B2dWorld {
    private World world;
    private final Array<B2dBody> bodies = new Array<>();


    private final float STEP_TIME = 1;
    private final int VELOCITY_ITERATION = 1;
    private final int POSITION_ITERATION = 1;

    private float ppm;
    private float accumulator = 0f;

    public B2dWorld(){
        world = new World(new Vector2(), true);
    }


    public void step(float dt){
        accumulator += dt;
        while (accumulator >= STEP_TIME) {
            world.step(STEP_TIME, VELOCITY_ITERATION, POSITION_ITERATION);
            processEvents();
            accumulator -= STEP_TIME;
        }
    }
    private void processEvents() {
        for (int i = 0, n = bodies.size; i < n; i++) {
            B2dBody body = bodies.get(i);
            B2dEventContainer eventContainer = body.getEventContainer();
            for (B2dBaseEvent b2dBaseEvent : eventContainer.getEventArray()) {
                if(b2dBaseEvent.getEventType() == B2dEventsEnum.Destroy.ordinal())
                b2dBaseEvent.apply(body.getBody());
            }

            if (bodyComp.doDestroy) {
                bodyComp.destroyBody(world);
                entity.remove(B2dComponent.class);
            } else {
                bodyComp.processAllEvents();
            }
        }
    }

}
