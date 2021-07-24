package com.nzt.gdx.b2d.ashley.components;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.ashley.components.mvt.PositionComponent;
import com.nzt.gdx.ashley.components.mvt.Velocity2DComponent;
import com.nzt.gdx.b2d.events.B2dBaseEvent;

public class B2dEntityUtils {
    private static final ComponentMapper<B2dBodyComponent> b2dMapper = B2dBodyComponent.mapper;
    private static final ComponentMapper<PositionComponent> posMapper = PositionComponent.mapper;
    private static final ComponentMapper<Velocity2DComponent> velocityMapper = Velocity2DComponent.mapper;

    public static void addBox2DEvent(B2dBaseEvent event, Entity entity) {
        b2dMapper.get(entity).addBox2DEvent(event);
    }

    public static void updatePositionFromBody(Entity entity) {
        B2dBodyComponent b2DBodyComponent = b2dMapper.get(entity);

        Vector2 position = b2DBodyComponent.body.getPosition();

        PositionComponent positionComponent = posMapper.get(entity);
        positionComponent.setPosition(position);
    }

    public static void updateAngleFromBody(Entity entity) {
        B2dBodyComponent b2DBodyComponent = b2dMapper.get(entity);

        float angle = b2DBodyComponent.body.getAngle();
        posMapper.get(entity).angleRadian = angle;
    }

    public static void updateVelocityFromBody(Entity entity) {
        Velocity2DComponent velocity2DComponent = velocityMapper.get(entity);
        if (velocity2DComponent != null) { // les static n'ont pas de vel
            B2dBodyComponent b2DBodyComponent = B2dBodyComponent.mapper.get(entity);
            Vector2 linearVelocity = b2DBodyComponent.body.getLinearVelocity();
            velocity2DComponent.setVelocity(linearVelocity);
        }
    }
}
