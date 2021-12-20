package com.nzt.b2d.test.api;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.nzt.b2d.factories.B2dBodyFactory;
import com.nzt.b2d.test.screens.B2dTestConstants;
import com.nzt.gdx.test.st.tester.UnitTestScreen;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseB2dTest extends UnitTestScreen {
    public World world;
    public B2dBodyFactory bodyFactory;

    public BaseB2dTest(FastTesterMain main) {
        super(main);
    }

    public BaseB2dTest() {
        super();
    }

    @BeforeEach
    public void initWorld() {
        this.world = new World(Vector2.Zero, true);
        this.bodyFactory = new B2dBodyFactory(world, B2dTestConstants.PPM);
    }

    @Override
    public void disposeTestScreen() {
        world.dispose();
    }

    @Override
    public void renderTestScreen(float dt) {
        world.step(dt, 6, 2);

    }
}
