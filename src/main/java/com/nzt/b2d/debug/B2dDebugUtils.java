package com.nzt.b2d.debug;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.nzt.gdx.logger.TagLoggerBlockUtils;
import com.nzt.gdx.logger.tag.LogTagsBase;
import com.nzt.gdx.logger.tag.TagLogger;

public class B2dDebugUtils {

	private B2dDebugUtils() {

	}

	public static String debugFilter(Filter filter) {
		return "categoryBits=" + filter.categoryBits + ", maskBits=" + filter.maskBits + ", groupIndex="
				+ filter.groupIndex;
	}

	public static void debugBody(Body body) {
		TagLoggerBlockUtils.startBlockDebug(LogTagsBase.DEBUG, "Body Debug");
		TagLogger.debug(LogTagsBase.DEBUG, "Type", body.getType() + "");
		if (body.getUserData() instanceof String) {
			TagLogger.debug(LogTagsBase.DEBUG, "UserData", body.getUserData().toString());
		} else {
			TagLogger.debug(LogTagsBase.DEBUG, "UserData", body.getUserData().getClass().getSimpleName());
		}
		Array<Fixture> fixtures = body.getFixtureList();
		int i = 0;
		for (Fixture fixture : fixtures) {
			debugFixture(fixture, i);
			i++;
		}
		TagLoggerBlockUtils.endBlockDebug(LogTagsBase.DEBUG, "Body Debug");

	}

	public static void debugFixture(Fixture fixture, int i) {
		TagLoggerBlockUtils.startBlockDebug(LogTagsBase.DEBUG, "Fixture " + i + " Debug");
		Object userData = fixture.getUserData();
		if (userData instanceof String) {
			TagLogger.debug(LogTagsBase.DEBUG, "UserData", userData.toString());
		} else {
			TagLogger.debug(LogTagsBase.DEBUG, "UserData", userData.getClass().getSimpleName());
		}
		TagLogger.debug(LogTagsBase.DEBUG, "FilterDate", debugFilter(fixture.getFilterData()));
		TagLoggerBlockUtils.endBlockDebug(LogTagsBase.DEBUG, "Fixture Debug");
	}

	public static void logInformation(World world, int logLevel) {
		if (world != null) {
			TagLoggerBlockUtils.startBlock(logLevel, LogTagsBase.B2D_INFO, "Box2D World Recap");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "Body count", world.getBodyCount() + "");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "Contact count", world.getContactCount() + "");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "Fixture count", world.getFixtureCount() + "");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "Joint count", world.getJointCount() + "");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "Proxy count", world.getProxyCount() + "");
			TagLogger.logWithLevel(logLevel, LogTagsBase.B2D_INFO, "World VelocityThreshold",
					World.getVelocityThreshold() + "");
			TagLoggerBlockUtils.endBlock(logLevel, LogTagsBase.B2D_INFO, "Box2D World Recap");
		} else {
			TagLogger.debug(LogTagsBase.B2D_CONTACT, "Box2D Debug", "No World Created");
		}
	}

	// TODO a voir si vraiment utile ...
	public static void debugContact(String eventName, Contact contact) {
		TagLoggerBlockUtils.startBlockDebug(LogTagsBase.B2D_CONTACT, "Contact Debug");
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getChildIndexA", "" + contact.getChildIndexA());
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getChildIndexB", "" + contact.getChildIndexB());

		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getFriction", "" + contact.getFriction());
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getRestitution", "" + contact.getRestitution());
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getTangentSpeed", "" + contact.getTangentSpeed());
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "isTouching", "" + contact.isTouching());

		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getWorldManifold().getNormal()",
				"" + contact.getWorldManifold().getNormal());
		int numberOfContactPoints = contact.getWorldManifold().getNumberOfContactPoints();
		TagLogger.debug(LogTagsBase.B2D_CONTACT, "getWorldManifold().getNumberOfContactPoints()",
				"" + numberOfContactPoints);
		for (int i = 0, n = numberOfContactPoints; i < n; i++) {
			TagLogger.debug(LogTagsBase.B2D_CONTACT, "contact point " + (i + 1),
					"" + contact.getWorldManifold().getPoints()[i]);
		}
		TagLoggerBlockUtils.endBlockDebug(LogTagsBase.B2D_INFO, "Contact Debug");
	}
}
