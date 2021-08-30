package com.nzt.b2d.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.gdx.ashley.NztSystemsOrder;
import com.nzt.gdx.ashley.base.systems.NzIteratingSystem;
import com.nzt.b2d.ashley.components.B2dBodyComponent;

/**
 * apply b2D event
 */
public class B2dApplyEventsSystem extends NzIteratingSystem {
	private final static ComponentMapper<B2dBodyComponent> b2dMapper = B2dBodyComponent.mapper;

	private final World world;

	public B2dApplyEventsSystem(World world, int order) {
		super(Family.all(B2dBodyComponent.class).get(), order);
		this.world = world;
	}

	public B2dApplyEventsSystem(World world) {
		this(world, NztSystemsOrder.B2D_EVENTS);
	}


	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		B2dBodyComponent bodyComp = b2dMapper.get(entity);
		if (bodyComp.doDestroy) {
			bodyComp.destroyBody(world);
		} else {
			bodyComp.processAllEvents();
		}
	}
}
