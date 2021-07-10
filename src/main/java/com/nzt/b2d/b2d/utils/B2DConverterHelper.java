package com.nzt.b2d.b2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.gdx.logger.tag.LogTagsBase;
import com.nzt.gdx.logger.tag.TagLogger;

//TODO a revoir complet (use pools
public class B2DConverterHelper {
    public float PPM;

    public B2DConverterHelper(float ppm) {
        this.PPM = ppm;
        TagLogger.info(LogTagsBase.B2D_INFO, "Box2DConverter PPM = " + PPM);
    }

    public float heightScreenToPPM() {
        return Gdx.graphics.getHeight() / PPM;
    }

    public float widthScreenToPPM() {
        return Gdx.graphics.getWidth() / PPM;
    }

    public float toPPM(float x) {
        return x / PPM;
    }

    public Vector2 newToPPM(Vector2 vector) {
        return new Vector2(vector.x / PPM, vector.y / PPM);
    }

    public Vector2 toPPM(float x, float y) {
        return toPPM(new Vector2(x, y));
    }

    public Vector2 toPPM(Vector2 vector) {
        return vector.set(vector.x / PPM, vector.y / PPM);
    }

    public Vector3 newToPPM(Vector3 vector) {
        return new Vector3(vector.x / PPM, vector.y / PPM, vector.z / PPM);
    }

    public Vector3 toPPM(Vector3 vector) {
        return vector.set(vector.x / PPM, vector.y / PPM, vector.z / PPM);
    }

    public Rectangle toPPM(Rectangle rect) {
        return rect.set(rect.getX() / PPM, rect.getY() / PPM, rect.getWidth() / PPM, rect.getHeight() / PPM);
    }

    public float toScreen(float x) {
        return x * PPM;
    }

    public Vector2 newToScreen(Vector2 vector) {
        return new Vector2(vector.x * PPM, vector.y * PPM);
    }

    public Vector2 newToToScreen(Vector2 vector) {
        return new Vector2(vector.x * PPM, vector.y * PPM);
    }

    public Vector2 toScreen(Vector2 vector) {
        return vector.set(vector.x * PPM, vector.y * PPM);
    }

    public Vector3 newToScreen(Vector3 vector) {
        return new Vector3(vector.x * PPM, vector.y * PPM, vector.z * PPM);
    }

    public Vector3 toScreen(Vector3 vector) {
        return vector.set(vector.x * PPM, vector.y * PPM, vector.z * PPM);
    }

    /**
     * Return la position relative d'un pointer par rapport a un body en unité box2D
     *
     * @param screenX
     * @param screenY
     * @param body
     * @return
     */
//	public static Vector2 relativePositionMouseFromOtherPoint(int screenX, int screenY, Vector2 position) {
//		Vector2 screenMetricsBody = toScreenMetrics(position);
//		Vector2 mousePosition = InputCalculUtils.relativePositionPointerFromPoint(screenX, screenY, screenMetricsBody);
//		return toBox2DMetrics(mousePosition);
//	}
}
