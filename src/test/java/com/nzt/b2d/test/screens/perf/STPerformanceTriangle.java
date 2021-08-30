package com.nzt.b2d.test.screens.perf;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.test.screens.B2dTestConstants;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.math.shapes.builders.TriangleBuilder;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STPerformanceTriangle extends BaseB2DPerfST {

    FixtureDefWrapper fixtureDefWrapper;
    boolean fixedRotation = true;
    Triangle triangle;

    public STPerformanceTriangle(FastTesterMain main) {
        super(main);
        createWallScreen();
        fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody);
        fixtureDefWrapper.setFriction(0);
        fixtureDefWrapper.setRestitution(1);
        fixtureDefWrapper.setDensity(1);

        triangle = TriangleBuilder.equilateral(0, new Vector2(), 15 / B2dTestConstants.PPM);

        add100Bodies();
    }

    @Override
    protected Body createBody(Vector2 position) {
        Vector2 pos = randomPos();
        triangle.setPosition(pos);
        Body triangleBody = bodyFactory.createPolygonBody(triangle.getTransformedVertices(), fixtureDefWrapper);
        return triangleBody;
    }

    @Override
    public String getTestExplication() {
        return "Test perf with triangle";
    }
}
