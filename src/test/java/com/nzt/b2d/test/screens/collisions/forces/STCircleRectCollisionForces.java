package com.nzt.b2d.test.screens.collisions.forces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DTestScreen;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "collisions.forces")
public class STCircleRectCollisionForces extends BaseB2DTestScreen {
    Body circleBody, rectBody;

    Vector2 posCircle = new Vector2(-5, -5);
    Vector2 velCircle = new Vector2(-5, -5);

    public STCircleRectCollisionForces(FastTesterMain main) {
        super(main);
        FixtureDefWrapper fixture = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false).setRestitution(1)
                .setDensity(1).setToPPM(false);
        circleBody = bodyFactory.createCircleBody(-5, 0, 0.5f, fixture);
        rectBody = bodyFactory.createRectangleBody(new Rectangle(-1, -2, 2, 15), fixture);
        rectBody.setFixedRotation(true);

        infoMsg("rectBody.isFixedRotation() : " + rectBody.isFixedRotation());
        afterClick(null);
    }

    @Override
    public void afterClick(Vector2 posClicked) {
        circleBody.setTransform(-5, -5, 0);
        circleBody.setLinearVelocity(4, 2);

        rectBody.setTransform(5, -2, 0);
        rectBody.setLinearVelocity(-2, 0);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("Circle velocity", circleBody.getLinearVelocity());
        debugMsg("Rectangle velocity", rectBody.getLinearVelocity());
    }

    @Override
    public String getTestExplication() {
        return "Test collision where only one body should give force";
    }
}
