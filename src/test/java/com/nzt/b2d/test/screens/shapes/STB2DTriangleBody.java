package com.nzt.b2d.test.screens.shapes;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.test.runner.BaseB2DSystemTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen
public class STB2DTriangleBody extends BaseB2DSystemTestScreen {
    Body triangleBody;
    Entity entity;

    public STB2DTriangleBody(FastTesterMain main) {
        super(main);
        FixtureDefWrapper fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.KinematicBody).setSensor(false)
                .setDensity(1).setToPPM(false);

        Vector2 a = new Vector2(0, 0);
        Vector2 b = new Vector2(5, 0);
        Vector2 c = new Vector2(5, 5);
        triangleBody = bodyFactory.createPolygonBody(new Vector2[]{a, b, c}, fixtureDefWrapper);

        entity = addEntityBody(triangleBody);
        triangleBody.setAngularVelocity(1);
        SimpleClickInputHandler clickInputHandler = new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                transformToPPM(triangleBody, screenX, screenY, 0);
                return false;
            }

        };
        Gdx.input.setInputProcessor(clickInputHandler);
    }

    @Override
    public String getTestExplication() {
        return "Triangle shape sur Box2D";
    }

}
