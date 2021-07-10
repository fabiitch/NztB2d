package com.nzt.b2d.b2d.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class B2DUtils {
	private B2DUtils() {

	}

	public static boolean shouldCollide(FixtureDef fixtureA, FixtureDef fixtureB) {
		Filter filterA = fixtureA.filter;
		Filter filterB = fixtureB.filter;
		if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0)
			return filterA.groupIndex >= 0;

		boolean collideA = (filterA.maskBits & filterB.categoryBits) != 0;
		boolean collideB = (filterA.categoryBits & filterB.maskBits) != 0;
		boolean collide = collideA && collideB;
		return collide;
	}

	public static boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();
		return (filterA.maskBits & filterB.categoryBits) != 0 && (filterA.categoryBits & filterB.maskBits) != 0;
	}

	public static boolean shouldCollide(Body bodyA, Body bodyB) {
		Array<Fixture> fixtureListA = bodyA.getFixtureList();
		Array<Fixture> fixtureListB = bodyB.getFixtureList();

		for (Fixture fixtureA : fixtureListA) {
			for (Fixture fixtureB : fixtureListB) {
				if (shouldCollide(fixtureA, fixtureB))
					return true;
			}
		}
		return false;
	}
}
