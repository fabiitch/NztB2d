package com.nzt.b2d.test.screens.collisions.forces;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "collisions.forces")
public class STBallTwoDirCollision extends BaseB2DTestScreen {
    Body ball1, ball2;

    final float RESTITUTION = 0f;
    final float DENSITY = 1f;

    public STBallTwoDirCollision(FastTesterMain main) {
        super(main);

        FixtureDefWrapper fixture = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false).setRestitution(RESTITUTION)
                .setDensity(DENSITY).setToPPM(false);
        ball1 = bodyFactory.createCircleBody(-5, 0, 0.5f, fixture);
        ball1.setFixedRotation(true);
        ball1.setLinearVelocity(5, 0);

        ball2 = bodyFactory.createCircleBody(0, -5, 0.5f, fixture);
        ball2.setFixedRotation(true);
        ball2.setLinearVelocity(0, 5);
        afterClick(null);
        infoMsg("Restitution = " + RESTITUTION);
        infoMsg("Density = " + DENSITY);
    }

    @Override
    public void afterClick(Vector2 posClicked) {
        ball1.setTransform(-5, 0, 0);
        ball1.setLinearVelocity(5, 0);

        ball2.setTransform(0, -5, 0);
        ball2.setLinearVelocity(0, 5);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("VelocityA", ball1.getLinearVelocity());
        debugMsg("VelocityB", ball2.getLinearVelocity());
    }

    @Override
    public String getTestExplication() {
        return null;
    }
}
