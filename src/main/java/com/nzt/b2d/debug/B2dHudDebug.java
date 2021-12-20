package com.nzt.b2d.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;

//TODO si pas use a delete
public class B2dHudDebug {


    public B2dHudDebug(World world) {
        this(world, HudDebugPosition.TOP_RIGHT, Color.RED);
    }

    public B2dHudDebug(World world, int positionOnStage, Color color) {
        HudDebug.add("Body count", world.getBodyCount(), positionOnStage, color);
        HudDebug.add("Contact count", world.getContactCount(), positionOnStage, color);
        HudDebug.add("Fixture count", world.getFixtureCount(), positionOnStage, color);
        HudDebug.add("Joint count", world.getJointCount(), positionOnStage, color);
        HudDebug.add("Proxy count", world.getProxyCount(), positionOnStage, color);
        HudDebug.add("World VelocityThreshold", World.getVelocityThreshold(), positionOnStage, color);
    }

    public void remove() {
        HudDebug.remove("Body count");
        HudDebug.remove("Contact count");
        HudDebug.remove("Fixture count");
        HudDebug.remove("Joint count");
        HudDebug.remove("Proxy count");
        HudDebug.remove("World VelocityThreshold");

    }

    public void update(World world) {
        HudDebug.update("Body count", world.getBodyCount());
        HudDebug.update("Contact count", world.getContactCount());
        HudDebug.update("Fixture count", world.getFixtureCount());
        HudDebug.update("Joint count", world.getJointCount());
        HudDebug.update("Proxy count", world.getProxyCount());
        HudDebug.update("World VelocityThreshold", World.getVelocityThreshold());
    }

}
