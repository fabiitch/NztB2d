package com.nzt.b2d.test.screens.events;

import java.util.concurrent.Callable;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nzt.b2d.test.runner.BaseB2DSystemTestScreen;
import com.nzt.b2d.FixtureDefWrapper;
import com.nzt.b2d.ashley.components.B2dBodyComponent;
import com.nzt.b2d.events.type.fixture.impl.SensorFixtureEvent;
import com.nzt.b2d.factories.B2dFixtureEventFactory;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList
public class STFixtureEvent extends BaseB2DSystemTestScreen {
    Body circleBody;
    Entity entity;

    public STFixtureEvent(FastTesterMain main) {
        super(main);
        FixtureDefWrapper fixtureDefWrapper = new FixtureDefWrapper(BodyDef.BodyType.DynamicBody).setSensor(false)
                .setDensity(1).setToPPM(false);
        circleBody = bodyFactory.createCircleBody(Vector2.Zero, 2, fixtureDefWrapper);

        entity = addEntityBody(circleBody);
        HudDebug.addTopLeft("Should be sensor after", 200 + " renderCalls");
        HudDebug.addTopLeft("Fixture isSensor", circleBody.getFixtureList().get(0).isSensor());
        addFunctionToCall(200, fireEvent());
        createWallScreen();
    }

    @Override
    public String getTestExplication() {
        return "Fixture Event";
    }

    @Override
    public void renderTestScreen(float dt) {
        super.renderTestScreen(dt);
        HudDebug.update("Fixture isSensor", circleBody.getFixtureList().get(0).isSensor());
    }

    private Callable<Boolean> fireEvent() {
        return new Callable<Boolean>() {
            public Boolean call() {
                SensorFixtureEvent sensorFixtureEvent = B2dFixtureEventFactory.sensor(-1, true);
                entity.getComponent(B2dBodyComponent.class).addBox2DEvent(sensorFixtureEvent);
                HudDebug.updateColor("Fixture isSensor", Color.BLUE);
                return true;
            }
        };
    }
}
