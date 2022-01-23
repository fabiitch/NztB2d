package com.nzt.b2d.factories;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;


public class B2dShapeFactory {

    public PolygonShape rectangle(Rectangle rect) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
        return shape;
    }

    public PolygonShape polygon(Polygon polygon) {
        PolygonShape shape = new PolygonShape();
        shape.set(polygon.getTransformedVertices());
        return shape;
    }
    public CircleShape circle(Circle circle){
        CircleShape shape = new CircleShape();
        shape.setRadius(circle.radius);
        return shape;
    }
}
