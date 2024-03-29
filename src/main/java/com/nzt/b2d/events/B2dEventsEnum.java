package com.nzt.b2d.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum B2dEventsEnum {
    Active(B2dEventTarget.Body),
    Awake(B2dEventTarget.Body),
    MassData(B2dEventTarget.Body),
    GravityScale(B2dEventTarget.Body),
    SleepingAllowed(B2dEventTarget.Body),
    Bullet(B2dEventTarget.Body),
    FixedRotation(B2dEventTarget.Body),
    BodyType(B2dEventTarget.Body),
    Destroy(B2dEventTarget.Body),
    Transform(B2dEventTarget.Body),
    Rotation(B2dEventTarget.Body),
    LinearVelocity(B2dEventTarget.Body),
    ApplyForce(B2dEventTarget.Body),
    ApplyForceToCenter(B2dEventTarget.Body),
    AngularVelocity(B2dEventTarget.Body),
    AngularImpulse(B2dEventTarget.Body),
    AngularDamping(B2dEventTarget.Body),
    Torque(B2dEventTarget.Body),
    LinearImpulse(B2dEventTarget.Body),


    FixtureAdd(B2dEventTarget.Fixture),
    FixtureDestroy(B2dEventTarget.Fixture),
    FixtureDefChange(B2dEventTarget.Fixture),
    FixtureSensor(B2dEventTarget.Fixture),
    FixtureFilter(B2dEventTarget.Fixture);


    private B2dEventTarget target;
}
