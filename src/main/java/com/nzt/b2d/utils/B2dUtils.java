package com.nzt.b2d.utils;

public class B2dUtils {
    private B2dUtils() {

    }
//    public static Vector2 getVelocityTo(Body body, float time, Vector2 posTo) {
//        final Vector2 bodyPos = body.getPosition();
//        Vector2 directionTo = directionTo(body, posTo);
//        float dst = bodyPos.dst(posTo);
//
//        Vector2 scl = directionTo.scl(dst / time);
//        return scl;
//    }
//
//    public static Vector2 directionTo(Body bodyFrom, Body bodyTo) {
//        final Vector2 fromPos = bodyFrom.getPosition();
//        final Vector2 toPos = bodyTo.getPosition();
//        return fromPos.cpy().sub(toPos).nor();
//    }
//
//    /**
//     * direction vector is nor
//     */
//    public static Vector2 directionTo(Body bodyFrom, Vector2 to) {
//        final Vector2 fromPos = bodyFrom.getPosition();
//        return fromPos.cpy().sub(to).nor();
//    }
//
//    public static double getBodySpeed(Body body) {
//        float x = body.getLinearVelocity().x;
//        float y = body.getLinearVelocity().y;
//        return Math.sqrt(x * x + y * y);
//    }
//
//    public static float dst(Body body1, Body body2) {
//        return body1.getPosition().dst(body2.getPosition());
//    }

}
