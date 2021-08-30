package com.nzt.b2d.test.utils;

import com.badlogic.ashley.core.Engine;
import com.nzt.gdx.ashley.base.factories.BaseEntityFactory;
import com.nzt.b2d.ashley.factory.B2dComponentFactory;

public class B2dEntityFactory extends BaseEntityFactory {
    public B2dComponentFactory b2DFactory;

    public B2dEntityFactory(Engine engine) {
        super(engine);
        this.b2DFactory = new B2dComponentFactory(engine);
    }
}
