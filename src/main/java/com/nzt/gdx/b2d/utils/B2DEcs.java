package com.nzt.gdx.b2d.utils;

import com.badlogic.ashley.core.Entity;
import com.nzt.gdx.ashley.components.b2d.B2DBodyComponent;
import com.nzt.gdx.b2d.events.B2DBaseEvent;


public class B2DEcs {

    public static void addBox2DEvent(B2DBaseEvent event, Entity entity) {
        B2DBodyComponent.mapper.get(entity).addBox2DEvent(event);
    }
}
