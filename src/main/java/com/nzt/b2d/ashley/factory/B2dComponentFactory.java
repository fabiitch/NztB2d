package com.nzt.b2d.ashley.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.gdx.ashley.factories.BaseComponentFactory;
import com.nzt.b2d.ashley.components.B2dBodyComponent;

public class B2dComponentFactory extends BaseComponentFactory {
    public B2dComponentFactory(Engine engine) {
        super(engine);
    }

    public B2dBodyComponent b2DBody(Body body) {
        B2dBodyComponent box2dBodyComponent = engine.createComponent(B2dBodyComponent.class);
        box2dBodyComponent.body = body;
        return box2dBodyComponent;
    }
}
