package com.nzt.gdx.b2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class B2DConverter {

	public static float heightScreenToPPM(float PPM) {
		return Gdx.graphics.getHeight() / PPM;
	}

	public static float widthScreenToPPM(float PPM) {
		return Gdx.graphics.getWidth() / PPM;
	}

	public static float toPPM(float x, float PPM) {
		return x / PPM;
	}

	public static Vector2 newToPPM(Vector2 vector, float PPM) {
		return new Vector2(vector.x / PPM, vector.y / PPM);
	}

	public static Vector2 toPPM(Vector2 vector, float PPM) {
		return vector.set(vector.x / PPM, vector.y / PPM);
	}

	public static Vector3 newToPPM(Vector3 vector, float PPM) {
		return new Vector3(vector.x / PPM, vector.y / PPM, vector.z / PPM);
	}

	public static Vector3 toPPM(Vector3 vector, float PPM) {
		return vector.set(vector.x / PPM, vector.y / PPM, vector.z / PPM);
	}

	public static Rectangle toPPM(Rectangle rect, float PPM) {
		return rect.set(rect.getX() / PPM, rect.getY() / PPM, rect.getWidth() / PPM, rect.getHeight() / PPM);
	}

	public static float toScreen(float x, float PPM) {
		return x * PPM;
	}

	public static Vector2 newToScreen(Vector2 vector, float PPM) {
		return new Vector2(vector.x * PPM, vector.y * PPM);
	}

	public static Vector2 newToToScreen(Vector2 vector, float PPM) {
		return new Vector2(vector.x * PPM, vector.y * PPM);
	}

	public static Vector2 toScreen(Vector2 vector, float PPM) {
		return vector.set(vector.x * PPM, vector.y * PPM);
	}

	public static Vector3 newToScreen(Vector3 vector, float PPM) {
		return new Vector3(vector.x * PPM, vector.y * PPM, vector.z * PPM);
	}

	public static Vector3 toScreen(Vector3 vector, float PPM) {
		return vector.set(vector.x * PPM, vector.y * PPM, vector.z * PPM);
	}

}
