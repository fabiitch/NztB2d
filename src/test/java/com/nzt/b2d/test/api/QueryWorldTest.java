package com.nzt.b2d.test.api;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryWorldTest extends BaseB2dTest {
    public QueryWorldTest(FastTesterMain main) {
        super(main);
    }

    public QueryWorldTest() {
        super();
    }

    @Test
    public void queryWorldTest() {
        FixtureDefWrapper fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody);
        fixtureDefWrapper.setUserData("TOTO");
        Body circleBody = bodyFactory.createCircleBody(0, 0, 1, fixtureDefWrapper);

        final int[] reportFixtureCall = {0};
        QueryCallback queryCallback = new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                Assertions.assertTrue(fixture.getBody() == circleBody);
                Assertions.assertTrue(fixture.getUserData().equals("TOTO"));
                reportFixtureCall[0]++;
                return false;
            }
        };
        world.QueryAABB(queryCallback, -1, -1, 1, 1);
        Assertions.assertEquals(1, reportFixtureCall[0]);

        reportFixtureCall[0] = 0;
        world.QueryAABB(queryCallback, -10, -10, -5, -5);
        Assertions.assertEquals(0, reportFixtureCall[0]);
    }

    @Override
    public void renderUnitTest(float dt) {

    }

    @Override
    public String getTestExplication() {
        return null;
    }

    @Override
    public void disposeTestScreen() {

    }
}
