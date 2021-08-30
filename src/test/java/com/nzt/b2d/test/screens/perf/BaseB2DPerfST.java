package com.nzt.b2d.test.screens.perf;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.b2d.test.screens.B2dTestConstants;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.debug.B2dHudDebug;
import com.nzt.b2d.factories.B2dBodyFactory;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.debug.utils.DebugDisplayUtils;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.math.Percentage;
import com.nzt.gdx.math.random.Randoms;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "perf")
public abstract class BaseB2DPerfST extends TestScreen {

    public final float PPM = B2dTestConstants.PPM;
    protected Camera camera;
    protected World world;
    protected Box2DDebugRenderer debugRenderer;
    protected B2dBodyFactory bodyFactory;
    protected B2dHudDebug b2dHudDebug;

    protected final String KEY_WORLD_TIME = "World time";
    protected final String KEY_RENDER_TIME = "Render time";

    protected final String KEY_WORLD_PERCENT = "World time %";
    protected final String KEY_RENDER_PERCENT = "Render time %";

    boolean fixedRotation = true;

    public BaseB2DPerfST(FastTesterMain main) {
        super(main);
        camera = new OrthographicCamera(B2dTestConstants.WIDTH_PPM, B2dTestConstants.HEIGHT_PPM);
        world = new World(new Vector2(), false);
        world.setWarmStarting(true);
        debugRenderer = new Box2DDebugRenderer();
        b2dHudDebug = new B2dHudDebug(world, HudDebugPosition.BOT_LEFT, Color.RED);
        bodyFactory = new B2dBodyFactory(world, B2dTestConstants.PPM);

        HudDebug.addTopRight(KEY_WORLD_TIME, 999999999);
        HudDebug.addTopRight(KEY_RENDER_TIME, 999999999);
        HudDebug.addTopRight("================", "===============");

        HudDebug.addTopRight(KEY_WORLD_PERCENT, 999999999);
        HudDebug.addTopRight(KEY_RENDER_PERCENT, 999999999);
        infoMsg("Percent as about 1/80 second");
        infoMsg("Rotation", !fixedRotation + "");

        SimpleClickInputHandler simpleClickInputHandler = new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                add100Bodies();
                return false;
            }
        };
        Gdx.input.setInputProcessor(simpleClickInputHandler);
    }

    public void add100Bodies() {
        for (int i = 0; i < 100; i++) {
            Body body = createBody(randomPos());
            body.setFixedRotation(fixedRotation);
            Vector2 vel = new Vector2(1, 0).setToRandomDirection().setLength(500 / PPM);
            body.setLinearVelocity(vel);
        }
    }

    protected abstract Body createBody(Vector2 position);


    protected Vector2 randomPos() {
        Vector2 pos = Randoms.random(-B2dTestConstants.WIDTH_PPM / 2 - 10, B2dTestConstants.WIDTH_PPM / 2 - 10 / PPM,
                -B2dTestConstants.HEIGHT_PPM / 2 - 10 / PPM, B2dTestConstants.HEIGHT_PPM / 2 - 10 / PPM);
        return pos;
    }

    int i = 0;

    @Override
    public void renderTestScreen(float dt) {
        b2dHudDebug.update(world);
        long startWorld = System.nanoTime();
        world.step(dt, 6, 2);
        long timeWorld = System.nanoTime() - startWorld;
        long startRender = System.nanoTime();
        debugRenderer.render(world, camera.combined);
        long timeRender = System.nanoTime() - startRender;
        i++;
        if (i >= 10) {
            HudDebug.update(KEY_WORLD_TIME, DebugDisplayUtils.printNanoToMs(timeWorld));
            HudDebug.update(KEY_WORLD_PERCENT,
                    Percentage.getPercent(timeWorld, TimeUnit.SECONDS.toNanos(1) / 80f) + " %");
            HudDebug.update(KEY_RENDER_TIME, DebugDisplayUtils.printNanoToMs(timeRender));
            HudDebug.update(KEY_RENDER_PERCENT,
                    Percentage.getPercent(timeRender, TimeUnit.SECONDS.toNanos(1) / 80f) + " %");
            i = 0;
        }

    }

    @Override
    public void disposeTestScreen() {
        world.dispose();
        debugRenderer.dispose();
    }

    /**
     * Walls autour du screen
     */
    public void createWallScreen() {
        Rectangle botHorizontal = new Rectangle(-com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH / 2, -com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT / 2,
                com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH, 1);
        Rectangle topHorizontal = new Rectangle(-com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH / 2, com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT / 2,
                com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH, 1);
        Rectangle leftVertical = new Rectangle(-com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH / 2, -com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT / 2, 1,
                com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT);
        Rectangle rightVertical = new Rectangle(com.nzt.gdx.test.trials.st.B2dTestConstants.WIDTH / 2, -com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT / 2, 1,
                com.nzt.gdx.test.trials.st.B2dTestConstants.HEIGHT);
        FixtureDefWrapper fixtureWall = new FixtureDefWrapper(BodyDef.BodyType.StaticBody).
                setSensor(false).setDensity(1)
                .setRestitution(1).setToPPM(true);

        bodyFactory.createRectangleBody(botHorizontal, fixtureWall);
        bodyFactory.createRectangleBody(topHorizontal, fixtureWall);
        bodyFactory.createRectangleBody(leftVertical, fixtureWall);
        bodyFactory.createRectangleBody(rightVertical, fixtureWall);
    }

}
