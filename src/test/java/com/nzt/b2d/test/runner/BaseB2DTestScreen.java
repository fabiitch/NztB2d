package com.nzt.b2d.test.runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.b2d.debug.B2dHudDebug;
import com.nzt.b2d.factories.B2dBodyFactory;
import com.nzt.b2d.utils.B2dConverterHelper;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.st.B2dTestConstants;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;

public abstract class BaseB2DTestScreen extends TestScreen {
    public final float PPM = B2dTestConstants.PPM;
    public B2dConverterHelper b2DConverter = new B2dConverterHelper(PPM);
    public final World world;
    public final Camera camera;
    public final B2dBodyFactory bodyFactory;
    public final Box2DDebugRenderer box2DDebugRenderer;

    public B2dHudDebug b2dHudDebug;

    public boolean simulationRun = true;

    public BaseB2DTestScreen(FastTesterMain main) {
        super(main);
        this.camera = new OrthographicCamera(B2dTestConstants.WIDTH_PPM, B2dTestConstants.HEIGHT_PPM);
        this.camera.position.set(0, 0, 0);
        this.camera.lookAt(0, 0, 0);
        this.world = new World(Vector2.Zero, true);
        this.bodyFactory = new B2dBodyFactory(world, B2dTestConstants.PPM);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.b2dHudDebug = new B2dHudDebug(world);

        Gdx.input.setInputProcessor(new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                afterClick(getClickPos(camera, screenX, screenY, new Vector2()));
                return false;
            }
        });

        box2DDebugRenderer.setDrawBodies(true);
        box2DDebugRenderer.setDrawVelocities(true);
        box2DDebugRenderer.setDrawJoints(true);
        box2DDebugRenderer.setDrawAABBs(true);
        box2DDebugRenderer.setDrawContacts(true);
        box2DDebugRenderer.setDrawInactiveBodies(true);

        infoMsg("Press Space to Stop simulation");
    }

    public abstract void afterClick(Vector2 posClicked);

    public abstract void doRender(float dt);

    @Override
    public void renderTestScreen(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            simulationRun = !simulationRun;
        }
        if (simulationRun) {
            world.step(dt, 10, 10);
            b2dHudDebug.update(world);
        }
        box2DDebugRenderer.render(world, camera.combined);
        doRender(dt);
    }

    @Override
    public void disposeTestScreen() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
