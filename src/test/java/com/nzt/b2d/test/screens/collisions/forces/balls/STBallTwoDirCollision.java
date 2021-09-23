package com.nzt.b2d.test.screens.collisions.forces.balls;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "collisions.forces")
public class STBallTwoDirCollision extends BaseSTBallCollision {
    public STBallTwoDirCollision(FastTesterMain main) {
        super(main);
    }

    @Override
    float restitution1() {
        return 0;
    }

    @Override
    float restitution2() {
        return 1;
    }

    @Override
    float mass1() {
        return 0;
    }

    @Override
    float mass2() {
        return 0;
    }

    @Override
    Vector2 posA() {
        return new Vector2(-5,0);
    }

    @Override
    Vector2 posB() {
        return new Vector2(0,-5);
    }

    @Override
    Vector2 velA() {
        return new Vector2(5,0);
    }

    @Override
    Vector2 velB() {
        return new Vector2(0,5);
    }

    @Override
    public String getTestExplication() {
        return null;
    }
}