package com.nzt.b2d.test;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.builders.BodyDefBuilder;
import com.nzt.b2d.builders.FixtureDefBuilder;
import com.nzt.b2d.factories.B2dShapeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeterminismTestOneBody {


    @Test
    public void t1() { //no collision one body
        World world = new World(new Vector2(), false);

        PolygonShape rectangle = B2dShapeFactory.rectangle(10, 20);

        BodyDef build = BodyDefBuilder.get().active(true).type(BodyDef.BodyType.DynamicBody).build();
        Body body = world.createBody(build);

        body.createFixture(FixtureDefBuilder.get().shape(rectangle).build());

        Vector2 posStart = body.getPosition().cpy();
        System.out.println("Position Start 1 =" + posStart);
        Array<Vector3> arrayInput = new Array<>();
        for (int i = 0; i < 10_000; i++) {
            Vector3 v = new Vector3();
            v.x = MathUtils.random(0.0001f, 3f);
            if (MathUtils.randomBoolean())
                v.x = -v.x;

            v.y = MathUtils.random(0.0001f, 3f);
            if (MathUtils.randomBoolean())
                v.y = -v.y;

            v.z = MathUtils.random(0.1f, 2);
            arrayInput.add(v);
        }

        Vector2 posEnd_1 = simulateInput(body, world, arrayInput);
        System.out.println("Position End 1 =" + posEnd_1);

        body.setTransform(posStart.x, posStart.y, 0);
        System.out.println("Position Start 2 =" + body.getPosition());

        Vector2 posEnd_2 = simulateInput(body, world, arrayInput);
        System.out.println("Position End 2 =" + posEnd_2);

        Assertions.assertEquals(posEnd_1, posEnd_2);
    }

    private Vector2 simulateInput(Body body, World world, Array<Vector3> arrayInput) {
        float time = 0;
        float DT = 1 / 400f;
        int indexInput = 0;
        boolean inputSend = false;
        while (indexInput < arrayInput.size - 1) {
            Vector3 input = arrayInput.get(indexInput);
            if (!inputSend) {
                body.setLinearVelocity(input.x, input.y);
                inputSend = true;
            }
            world.step(DT, 1, 1);
            time += DT;
            if (time > input.z) {
                time = 0;
                inputSend = false;
                indexInput++;
            }
        }
        return body.getPosition().cpy();
    }
}
