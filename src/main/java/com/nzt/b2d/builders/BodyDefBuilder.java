package com.nzt.b2d.builders;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyDefBuilder {
    private BodyDef bodyDef;

    public BodyDefBuilder() {
        this.bodyDef = new BodyDef();
    }

    public static BodyDefBuilder get(){
        return new BodyDefBuilder();
    }

    public BodyDef build() {
        BodyDef bodyDefFinish = this.bodyDef;
        this.bodyDef = new BodyDef();
        return bodyDefFinish;
    }

    public BodyDefBuilder type(BodyDef.BodyType bodyType) {
        bodyDef.type = bodyType;
        return this;
    }

    public BodyDefBuilder position(float x, float y) {
        bodyDef.position.set(x, y);
        return this;
    }

    public BodyDefBuilder position(Vector2 position) {
        bodyDef.position.set(position);
        return this;
    }

    public BodyDefBuilder linearVelocity(Vector2 linearVelocity) {
        bodyDef.linearVelocity.set(linearVelocity);
        return this;
    }


    public BodyDefBuilder angle(float angle) {
        bodyDef.angle = angle;
        return this;
    }

    public BodyDefBuilder linearDamping(float linearDamping) {
        bodyDef.linearDamping = linearDamping;
        return this;
    }

    public BodyDefBuilder angularDamping(float angularDamping) {
        bodyDef.angularDamping = angularDamping;
        return this;
    }

    public BodyDefBuilder allowSleep(boolean allowSleep) {
        bodyDef.allowSleep = allowSleep;
        return this;
    }

    public BodyDefBuilder awake(boolean awake) {
        bodyDef.awake = awake;
        return this;
    }

    public BodyDefBuilder fixedRotation(boolean fixedRotation) {
        bodyDef.fixedRotation = fixedRotation;
        return this;
    }

    public BodyDefBuilder bullet(boolean bullet) {
        bodyDef.fixedRotation = bullet;
        return this;
    }

    public BodyDefBuilder active(boolean active) {
        bodyDef.active = active;
        return this;
    }


    public BodyDefBuilder gravityScale(float gravityScale) {
        bodyDef.gravityScale = gravityScale;
        return this;
    }
}
