package com.nzt.b2d.debug;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class B2dUserDataDrawer {

    private final World world;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final Camera camera;

    private final Array<Body> bodies;

    public B2dUserDataDrawer(World world, Camera camera, float PPM) {
        this.world = world;
        this.camera = camera;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.bodies = new Array<>();
        font.getData().setScale(1 / PPM);
        font.setColor(Color.RED);
        font.setUseIntegerPositions(false);

    }

    public void drawUserData() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        world.getBodies(bodies);
        for (Body body : bodies) {
            Object userData = body.getUserData();
            Vector2 pos = body.getPosition();
            if (userData != null) {
                font.draw(spriteBatch, userData.toString(), pos.x, pos.y);
            }
        }
        spriteBatch.end();
    }

}
