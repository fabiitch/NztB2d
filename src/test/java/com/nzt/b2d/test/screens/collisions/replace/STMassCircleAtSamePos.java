package com.nzt.b2d.test.screens.collisions.replace;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DSystemTestScreen;
import com.nzt.b2d.test.screens.B2dTestConstants;
import com.nzt.gdx.ashley.systems.debug.DebugDisplaySystem;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "collisions.replace")
public class STMassCircleAtSamePos extends BaseB2DSystemTestScreen {
    public STMassCircleAtSamePos(FastTesterMain main) {
        super(main);
        createWallScreen();
        FixtureDefWrapper fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false)
                .setDensity(1).setToPPM(false);
        this.world.setGravity(new Vector2(0, 0));

        DebugDisplaySystem debugDisplaySystem = new DebugDisplaySystem(main.sb, camera, 1 / B2dTestConstants.PPM);
        engine.addSystem(debugDisplaySystem);
        for (int i = 0; i < 200; i++) {
            bodyFactory.createCircleBody(Vector2.Zero, 0.2f, fixtureDefWrapper);
        }
    }

    @Override
    public String getTestExplication() {
        return "Test de replace mass circle qui spawn au centre";
    }
}
