package com.nzt.b2d.ashley.systems;

public class B2dWorldConfig {

    boolean calculRotation = true;
    public float stepTime = 1 / 60f;
    public int velocityIterations = 6;
    public int positionIerations = 2;

    public B2dWorldConfig() {
    }

    public B2dWorldConfig(boolean calculRotation, float stepTime, int velocityIterations, int positionIerations) {
        this.calculRotation = calculRotation;
        this.stepTime = stepTime;
        this.velocityIterations = velocityIterations;
        this.positionIerations = positionIerations;
    }
}
