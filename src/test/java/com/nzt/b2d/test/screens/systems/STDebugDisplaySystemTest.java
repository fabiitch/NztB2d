package com.nzt.b2d.test.screens.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DSystemTestScreen;
import com.nzt.b2d.test.screens.B2dTestConstants;
import com.nzt.gdx.ashley.systems.debug.DebugDisplaySystem;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "ecs")
public class STDebugDisplaySystemTest extends BaseB2DSystemTestScreen {
    public STDebugDisplaySystemTest(FastTesterMain main) {
        super(main);
        this.world.setGravity(new Vector2(0, -1));
        createWallScreen();
        init();
        DebugDisplaySystem debugDisplaySystem = new DebugDisplaySystem(main.sb, camera, 1 / B2dTestConstants.PPM);
        engine.addSystem(debugDisplaySystem);
    }

    @Override
    public String getTestExplication() {
        return "STDebugDisplaySystemTest";
    }


    private void init() {
        float randomW = MathUtils.random(-B2dTestConstants.WIDTH_PPM / 2, B2dTestConstants.WIDTH_PPM / 2);
        float randomH = MathUtils.random(-B2dTestConstants.HEIGHT_PPM / 2, B2dTestConstants.HEIGHT_PPM / 2);
        FixtureDefWrapper fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false).setDensity(1).setToPPM(false);
        for (int i = 0; i < 200; i++) {
            float size = MathUtils.random(5 / B2dTestConstants.PPM, 50 / B2dTestConstants.PPM);
            Body circleBody = bodyFactory.createCircleBody(new Vector2(randomW, randomH), size, fixtureDefWrapper);
            Entity entity = addEntityBody(circleBody);
            entity.add(baseEntityFactory.mvtFactory.position());
            short mask;
            String name;
            if (i % 2 == 0) {
                mask = 12;
                name = "t1";
            } else {
                mask = 12;
                name = "t2";
            }
            entity.add(baseEntityFactory.propertiesFactory.type(mask, name));
            entity.add(baseEntityFactory.propertiesFactory.debug(i, name));
        }
    }
}
