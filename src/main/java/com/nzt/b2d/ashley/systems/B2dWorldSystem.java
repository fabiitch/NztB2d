package com.nzt.b2d.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.ashley.components.B2dBodyComponent;
import com.nzt.b2d.ashley.components.B2dEntityUtils;
import com.nzt.gdx.ashley.components.mvt.PositionComponent;
import com.nzt.gdx.debug.perf.PerformanceFrame;

/**
 * System for box2D world it do the world.step
 */
public class B2dWorldSystem extends IteratingSystem {
    private final static ComponentMapper<B2dBodyComponent> b2dMapper = B2dBodyComponent.mapper;

    private float accumulator = 0f;

    public B2dWorldConfig config;
    public final World world;

    public B2dWorldSystem(World world, B2dWorldConfig config, int order) {
        super(Family.all(B2dBodyComponent.class, PositionComponent.class).get(), order);
        this.config = config;
        this.world = world;
        PerformanceFrame.addSystem(this);
    }

    public void dispose() {
        this.world.dispose();
    }

    @Override
    public void update(float deltaTime) {
        PerformanceFrame.startSystem(this);
        ImmutableArray<Entity> entities = getEntities();

        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= config.stepTime) {
            world.step(config.stepTime, config.velocityIterations, config.positionIerations);
            for (int i = 0, n = entities.size(); i < n; i++) {
                Entity entity = entities.get(i);
                B2dBodyComponent bodyComp = b2dMapper.get(entity);
                if (bodyComp.doDestroy) {
                    bodyComp.destroyBody(world);
                    entity.remove(B2dBodyComponent.class);
                } else {
                    bodyComp.processAllEvents();
                }
            }
            accumulator -= config.stepTime;
        }
        // Entity Queue
        for (int i = 0, n = entities.size(); i < n; i++) {
            Entity entity = entities.get(i);
            B2dEntityUtils.updatePositionFromBody(entity);
            B2dEntityUtils.updateVelocityFromBody(entity);
            if (config.calculRotation)
                B2dEntityUtils.updateAngleFromBody(entity);
        }
        PerformanceFrame.endSystem(this);
    }

    //TODO a changer complet avec un listener
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }
}
