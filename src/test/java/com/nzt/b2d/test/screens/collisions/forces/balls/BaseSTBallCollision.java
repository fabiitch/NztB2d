package com.nzt.b2d.test.screens.collisions.forces.balls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DTestScreen;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "collisions.forces")
abstract class BaseSTBallCollision extends BaseB2DTestScreen {
    Body ball1, ball2;


    public BaseSTBallCollision(FastTesterMain main) {
        super(main);

        FixtureDefWrapper fixtureA = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false)
                .setRestitution(restitution1()).setDensity(1).setToPPM(false);
        ball1 = bodyFactory.createCircleBody(posA(), 0.5f, fixtureA);
        ball1.setFixedRotation(true);
        ball1.setUserData("1");
        ball1.setLinearVelocity(velA());
        ball1.getMassData().mass = mass1();
        ball1.setMassData(ball1.getMassData());

        FixtureDefWrapper fixtureB = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false)
                .setRestitution(restitution2()).setDensity(1).setToPPM(false);
        ball2 = bodyFactory.createCircleBody(posB(), 0.5f, fixtureB);
        ball2.setFixedRotation(true);
        ball2.setUserData("2");
        ball2.setLinearVelocity(velB());
        ball2.getMassData().mass = mass2();
        ball2.setMassData(ball2.getMassData());
        afterClick(null);

        infoMsg("--------------");
        infoMsg("Restitution 1 = " + restitution1());
        infoMsg("Mass 1= " + mass1());
        infoMsg("--------------");
        infoMsg("Restitution 2 = " + restitution2());
        infoMsg("Mass 2 = " + mass2());
    }

    abstract float restitution1();

    abstract float restitution2();

    abstract float mass1();

    abstract float mass2();

    abstract Vector2 posA();

    abstract Vector2 posB();

    abstract Vector2 velA();

    abstract Vector2 velB();

    @Override
    public void afterClick(Vector2 posClicked) {
        ball1.setTransform(posA(), 0);
        ball1.setLinearVelocity(velA());

        ball2.setTransform(posB(), 0);
        ball2.setLinearVelocity(velB());
    }

    @Override
    public void doRender(float dt) {
        debugMsg("VelocityA", ball1.getLinearVelocity());
        debugMsg("VelocityB", ball2.getLinearVelocity());
    }

}
