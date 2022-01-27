package com.nzt.b2d.builders;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.nzt.b2d.factories.B2dShapeFactory;

public class FixtureDefBuilder {

    private FixtureDef fixtureDef;

    public FixtureDefBuilder() {
        fixtureDef = new FixtureDef();
    }

    public static FixtureDefBuilder get() {
        return new FixtureDefBuilder();
    }

    public FixtureDef build() {
        FixtureDef fixtureDefFinish = this.fixtureDef;
        this.fixtureDef = new FixtureDef();
        return fixtureDefFinish;
    }

    public FixtureDefBuilder shape(Shape shape) {
        fixtureDef.shape = shape;
        return this;
    }

    public FixtureDefBuilder circleShape(float radius) {
        fixtureDef.shape = B2dShapeFactory.circle(radius);
        return this;
    }

    public FixtureDefBuilder polygonShape(Polygon polygon) {
        fixtureDef.shape = B2dShapeFactory.polygon(polygon);
        return this;
    }

    public FixtureDefBuilder rectangleShape(Rectangle rect) {
        fixtureDef.shape = B2dShapeFactory.rectangle(rect);
        return this;
    }

    public FixtureDefBuilder rectangleShape(Vector2 size) {
        fixtureDef.shape = B2dShapeFactory.rectangle(size.x, size.y);
        return this;
    }

    public FixtureDefBuilder rectangleShape(float witdh, float height) {
        fixtureDef.shape = B2dShapeFactory.rectangle(witdh, height);
        return this;
    }


    public FixtureDefBuilder friction(float friction) {
        fixtureDef.friction = friction;
        return this;
    }

    public FixtureDefBuilder restitution(float restitution) {
        fixtureDef.restitution = restitution;
        return this;
    }

    public FixtureDefBuilder density(float density) {
        fixtureDef.density = density;
        return this;
    }

    public FixtureDefBuilder isSensor(boolean isSensor) {
        fixtureDef.isSensor = isSensor;
        return this;
    }

    public FixtureDefBuilder filter(Filter filter) {
        filterCategoryBits(filter.categoryBits);
        filterMaskBits(filter.maskBits);
        filterGroupIndex(filter.groupIndex);
        return this;
    }

    public FixtureDefBuilder filterCategoryBits(short categoryBits) {
        fixtureDef.filter.categoryBits = categoryBits;
        return this;
    }

    public FixtureDefBuilder filterMaskBits(short maskBits) {
        fixtureDef.filter.maskBits = maskBits;
        return this;
    }

    public FixtureDefBuilder filterGroupIndex(short groupIndex) {
        fixtureDef.filter.groupIndex = groupIndex;
        return this;
    }

}
