package com.nzt.b2d.test.screens.perf;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STPerformanceBoucingBall extends BaseB2DPerfST {

    FixtureDefWrapper fixtureDefWrapper;
    boolean fixedRotation = true;

    public STPerformanceBoucingBall(FastTesterMain main) {
        super(main);
        infoMsg("Rotation", !fixedRotation + "");
        createWallScreen();
        fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody);
        fixtureDefWrapper.setFriction(0);
        fixtureDefWrapper.setRestitution(1);
        fixtureDefWrapper.setDensity(1);
        add100Bodies();
    }

    public Body createBody(Vector2 position) {
        return bodyFactory.createCircleBody(position, 5 / PPM, fixtureDefWrapper);
    }

    @Override
    public String getTestExplication() {
        return "Test perf with circle";
    }
}
