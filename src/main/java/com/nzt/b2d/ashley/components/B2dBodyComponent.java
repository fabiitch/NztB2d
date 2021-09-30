package com.nzt.b2d.ashley.components;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pools;
import com.nzt.b2d.ashley.systems.B2dWorldSystem;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventContainer;
import com.nzt.gdx.ashley.base.components.PoolableComponent;

/**
 * Box2D body component used by system : {@link B2dWorldSystem} and
 * {@link com.nzt.b2d.ashley.systems.B2dDebugSystem}
 */
public class B2dBodyComponent extends PoolableComponent {

    public static final ComponentMapper<B2dBodyComponent> mapper = ComponentMapper.getFor(B2dBodyComponent.class);

    public Body body;
    public B2dEventContainer eventContainer;
    public boolean doDestroy;

    public B2dBodyComponent() {
        super();
        this.eventContainer = new B2dEventContainer();
    }

    @Override
    public void reset() {
        this.body = null;
        this.doDestroy = false;
        this.eventContainer.reset();
    }

    public void addBox2DEvent(B2dBaseEvent... events) {
        eventContainer.addEvent(events);
    }

    public void addBox2DEvent(B2dBaseEvent event) {
        eventContainer.addEvent(event);
    }

    public void destroyBody(World world) {
        world.destroyBody(this.body);
        this.body = null;
    }

    public void processAllEvents() {
        for (B2dBaseEvent event : eventContainer.eventArray) {
            event.apply(body);
        }
        Pools.freeAll(eventContainer.eventArray);
        eventContainer.reset();
    }

}
