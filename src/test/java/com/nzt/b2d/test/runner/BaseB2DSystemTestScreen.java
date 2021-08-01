package com.nzt.b2d.test.runner;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.nzt.b2d.test.utils.B2dEntityFactory;
import com.nzt.gdx.b2d.FixtureDefWrapper;
import com.nzt.gdx.b2d.ashley.systems.B2dApplyEventsSystem;
import com.nzt.gdx.b2d.ashley.systems.B2dDebugSystem;
import com.nzt.gdx.b2d.ashley.systems.B2dWorldSystem;
import com.nzt.gdx.b2d.factories.B2dBodyFactory;
import com.nzt.gdx.b2d.utils.B2dConverterHelper;
import com.nzt.gdx.input.impl.simple.MouseInputHandler;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.st.B2dTestConstants;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.BaseSystemTestScreen;
import com.nzt.gdx.test.trials.tester.archi.systems.HudSystem;

import java.util.ArrayList;

public abstract class BaseB2DSystemTestScreen extends BaseSystemTestScreen {
    public final float PPM = B2dTestConstants.PPM;
    public B2dConverterHelper b2DConverter = new B2dConverterHelper(PPM);

    public World world;
    public Camera camera;
    public B2dBodyFactory bodyFactory;

    public B2dWorldSystem worldSystem;
    public B2dDebugSystem b2DDebugSystem;

    public ArrayList<Body> bodies = new ArrayList<>();
    public FixtureDefWrapper fixtureWall;

    private MouseJoint mouseJoint = null;
    Vector3 testPoint = new Vector3();
    Vector2 target = new Vector2();
    Body hitBody = null;
    protected Body groundBody;

    public B2dEntityFactory baseEntityFactory;

    public BaseB2DSystemTestScreen(FastTesterMain main) {
        super(main);

        baseEntityFactory = new B2dEntityFactory(engine);
        fixtureWall = new FixtureDefWrapper(BodyDef.BodyType.StaticBody).setSensor(false).setDensity(1).setToPPM(true);
        this.camera = new OrthographicCamera(B2dTestConstants.WIDTH_PPM, B2dTestConstants.HEIGHT_PPM);
        this.camera.position.set(0, 0, 0);
        this.camera.lookAt(0, 0, 0);

        HudSystem hudSystem = new HudSystem(nzStage);
        engine.addSystem(hudSystem);

        this.world = new World(Vector2.Zero, true);
        this.worldSystem = new B2dWorldSystem(world, true);
        B2dApplyEventsSystem b2DApplyEventsSystem = new B2dApplyEventsSystem(world);
        this.b2DDebugSystem = new B2dDebugSystem(world, camera);
        b2DDebugSystem.initHudDebug();

        bodyFactory = new B2dBodyFactory(world, B2dTestConstants.PPM);

        engine.addSystem(worldSystem);
        engine.addSystem(b2DApplyEventsSystem);
        engine.addSystem(b2DDebugSystem);

        // we also need an invisible zero size ground body
        // to which we can connect the mouse joint
        BodyDef bodyDef = new BodyDef();
        groundBody = world.createBody(bodyDef);
        QueryCallback callback = new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                if (fixture.testPoint(testPoint.x, testPoint.y)) {
                    hitBody = fixture.getBody();
                    return false;
                } else
                    return true;
            }
        };
        MouseInputHandler inputHandler = new MouseInputHandler() {

            @Override
            public boolean doTouchDown(int x, int y, int pointer, int newParam) {
                // translate the mouse coordinates to world coordinates
                testPoint.set(x, y, 0);
                camera.unproject(testPoint);

                // ask the world which bodies are within the given
                // bounding box around the mouse pointer
                hitBody = null;
                world.QueryAABB(callback, testPoint.x - 0.1f, testPoint.y - 0.1f, testPoint.x + 0.1f,
                        testPoint.y + 0.1f);

                // if we hit something we create a new mouse joint
                // and attach it to the hit body.
                if (hitBody != null) {
                    MouseJointDef def = new MouseJointDef();
                    def.bodyA = groundBody;
                    def.bodyB = hitBody;
                    def.collideConnected = true;
                    def.target.set(testPoint.x, testPoint.y);
                    def.maxForce = 1000.0f * hitBody.getMass();

                    mouseJoint = (MouseJoint) world.createJoint(def);
                    hitBody.setAwake(true);
                }

                return false;
            }

            @Override
            public boolean doTouchUp(int screenX, int screenY, int pointer, int button) {
                if (mouseJoint != null) {
                    world.destroyJoint(mouseJoint);
                    mouseJoint = null;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int x, int y, int pointer) {
                if (mouseJoint != null) {
                    camera.unproject(testPoint.set(x, y, 0));
                    mouseJoint.setTarget(target.set(testPoint.x, testPoint.y));
                }
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputHandler);
    }

    protected Vector2 v(Vector2 vector2) {
        return b2DConverter.toPPM(vector2);
    }

    /**
     * Walls autour du screen
     */
    public void createWallScreen() {
        Rectangle botHorizontal = new Rectangle(-B2dTestConstants.WIDTH / 2, -B2dTestConstants.HEIGHT / 2,
                B2dTestConstants.WIDTH, 1);
        Rectangle topHorizontal = new Rectangle(-B2dTestConstants.WIDTH / 2, B2dTestConstants.HEIGHT / 2,
                B2dTestConstants.WIDTH, 1);
        Rectangle leftVertical = new Rectangle(-B2dTestConstants.WIDTH / 2, -B2dTestConstants.HEIGHT / 2, 1,
                B2dTestConstants.HEIGHT);
        Rectangle rightVertical = new Rectangle(B2dTestConstants.WIDTH / 2, -B2dTestConstants.HEIGHT / 2, 1,
                B2dTestConstants.HEIGHT);

        bodyFactory.createRectangleBody(botHorizontal, fixtureWall);
        bodyFactory.createRectangleBody(topHorizontal, fixtureWall);
        bodyFactory.createRectangleBody(leftVertical, fixtureWall);
        bodyFactory.createRectangleBody(rightVertical, fixtureWall);
    }

    public void transformToPPM(Body body, Vector2 position, float angleRad) {
        transformToPPM(body, position.x, position.y, angleRad);
    }

    // screen to PPM
    public void transformToPPM(Body body, float x, float y, float angleRad) {
        Vector3 unproject = camera.unproject(new Vector3(x, y, 0));
        body.setTransform(unproject.x, unproject.y, angleRad);
    }

    protected void displayDebugRenderer(boolean display) {
        if (display)
            engine.addSystem(b2DDebugSystem);
        else
            engine.removeSystem(b2DDebugSystem);
    }

    public Entity addEntityBody(Body body) {
        this.bodies.add(body);
        Entity entity = baseEntityFactory.createEntity();
        entity.add(baseEntityFactory.b2DFactory.b2DBody(body));
        return entity;
    }

    @Override
    public void disposeTestScreen() {
        world.dispose();
        b2DDebugSystem.dispose();
    }
}
