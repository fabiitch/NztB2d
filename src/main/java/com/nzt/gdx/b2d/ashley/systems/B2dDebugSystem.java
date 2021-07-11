package com.nzt.gdx.b2d.ashley.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.gdx.ashley.NztSystemsOrder;
import com.nzt.gdx.ashley.base.systems.NzEntitySystem;
import com.nzt.gdx.b2d.debug.B2DHudDebug;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.perf.PerformanceFrame;

/**
 * System for Box2D, debugRender
 */
public class B2dDebugSystem extends NzEntitySystem {
    private final Box2DDebugRenderer debugRenderer;
    private final World world;
    private final Camera camera;

    private boolean displayHud = false;

    public B2dDebugSystem(World world, Camera camera, int order) {
        super(order);
        debugRenderer = new Box2DDebugRenderer();
        this.world = world;
        this.camera = camera;

        debugRenderer.setDrawBodies(true);
        debugRenderer.setDrawVelocities(true);
        debugRenderer.setDrawJoints(true);
        debugRenderer.setDrawAABBs(true);
        debugRenderer.setDrawContacts(true);
        debugRenderer.setDrawInactiveBodies(true);

        PerformanceFrame.addSystem(this);
    }

    public B2dDebugSystem(World world, Camera camera) {
        this(world, camera, NztSystemsOrder.B2D_DEBUG);
    }

    public void initHudDebug(int positionOnStage, Color color) {
        B2DHudDebug.initHudDebug(world, positionOnStage, color);
        this.displayHud = true;
    }

    public void initHudDebug() {
    	B2DHudDebug.initHudDebug(world, HudDebugPosition.TOP_RIGHT, Color.RED);
        this.displayHud = true;
    }

    public void updateHudDebug() {
    	B2DHudDebug.updateHudDebug(world);
    }

    @Override
    public void updateSystem(float dt) {
        debugRenderer.render(world, camera.combined);
        if (displayHud)
            updateHudDebug();
    }

    public void dispose() {
        this.debugRenderer.dispose();
    }

}
