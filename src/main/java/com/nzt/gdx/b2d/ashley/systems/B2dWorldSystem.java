package com.nzt.gdx.b2d.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.gdx.ashley.NztSystemsOrder;
import com.nzt.gdx.ashley.components.mvt.PositionComponent;
import com.nzt.gdx.b2d.ashley.components.B2dBodyComponent;
import com.nzt.gdx.b2d.ashley.components.B2dEntityUtils;
import com.nzt.gdx.debug.perf.PerformanceFrame;

/**
 * System for box2D world it do the world.step
 *
 */
public class B2dWorldSystem extends IteratingSystem {
	private final static ComponentMapper<B2dBodyComponent> b2dMapper = B2dBodyComponent.mapper;

	private static final float MAX_STEP_TIME = 1 / 60f;
	private float accumulator = 0f;

	private final World world;
	private final Array<Entity> bodiesQueue;
	private final Array<Entity> toRemove;

	private final boolean calculRotation;

	public B2dWorldSystem(World world, boolean calculRotation, int order) {
		super(Family.all(B2dBodyComponent.class, PositionComponent.class).get(), order);
		this.world = world;
		this.bodiesQueue = new Array<Entity>();
		this.toRemove = new Array();
		this.calculRotation = calculRotation;
		PerformanceFrame.addSystem(this);
	}

	public B2dWorldSystem(World world, boolean calculRotation) {
		this(world, calculRotation, NztSystemsOrder.B2D);
	}

	public void dispose() {
		this.world.dispose();
		this.bodiesQueue.clear();
	}

	public void setContactListener(ContactListener contactListener) {
		world.setContactListener(contactListener);
	}

	@Override
	public void update(float deltaTime) {
		PerformanceFrame.startSystem(this);
		super.update(deltaTime);
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		if (accumulator >= MAX_STEP_TIME) {
			while (accumulator >= MAX_STEP_TIME) {
				world.step(MAX_STEP_TIME, 6, 2);
				for (int i = 0, n = bodiesQueue.size; i < n; i++) {
					Entity entity = bodiesQueue.get(i);
					B2dBodyComponent bodyComp = b2dMapper.get(entity);
					if (bodyComp.doDestroy) {
						bodyComp.destroyBody(world);
						toRemove.add(entity);
					} else {
						bodyComp.processAllEvents();
					}
				}
				// remove entity destroyed
				for (int i = 0, n = toRemove.size; i < n; i++) {
					bodiesQueue.removeValue(toRemove.get(i), true);
				}
				toRemove.clear();

				accumulator -= MAX_STEP_TIME;
			}
			// Entity Queue
			for (int i = 0, n = bodiesQueue.size; i < n; i++) {
				Entity entity = bodiesQueue.get(i);
				B2dEntityUtils.updatePositionFromBody(entity);
				B2dEntityUtils.updateVelocityFromBody(entity);
				if (calculRotation)
					B2dEntityUtils.updateAngleFromBody(entity);
			}
		}
		bodiesQueue.clear();
		PerformanceFrame.endSystem(this);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		bodiesQueue.add(entity);
	}
}
