import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.nzt.b2d.builders.BodyDefBuilder;
import com.nzt.b2d.builders.FixtureDefBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class FullDeterminismTest {

    final int bodyCount = 50;
    final int inputCount = 1000;
    final float DT = 1 / 400f;
    final float SIMULATION_DURATION = 20; //seconds

    final Rectangle AREA_RECT = new Rectangle(-50, -50, 100, 100);
    Array<Vector2> posStart = new Array<>();
    Array<Vector2> bodySize = new Array<>();
    private HashMap<Integer, InputVelocity> inputsMap = new HashMap<>();

    @Test  //very long if u up body and duration
    public void fullTest() {
        System.out.println("Start Test with " + bodyCount + " bodies and duration of " + SIMULATION_DURATION);

        //create bodySize
        for (int i = 0; i < bodyCount; i++) {
            float witdh = MathUtils.random(0.001f, 5f);
            float height = MathUtils.random(0.001f, 5f);
            bodySize.add(new Vector2(witdh, height));
        }
        //create position
        for (int i = 0; i < bodyCount; i++) {
            posStart.add(getRandomPos(AREA_RECT));
        }

        //create inputVelocity
        for (int i = 0; i < bodyCount; i++) {
            InputVelocity inputVelocity = new InputVelocity(i);
            inputsMap.put(i, inputVelocity);
            for (int j = 0; j < inputCount; j++) {
                float x = MathUtils.random(0.0001f, 20f);
                if (MathUtils.randomBoolean())
                    x = -x;
                float y = MathUtils.random(0.0001f, 20f);
                if (MathUtils.randomBoolean())
                    y = -y;
                float time = MathUtils.random(0.1f, 4f);
                inputVelocity.add(x, y, time);
            }
        }

        System.out.println("========== Simulation 1 Start ==============");
        Array<Vector2> posEnd_1 = runSimulation(1);
        System.out.println("========== Simulation 1 End ==============");

        System.out.println("========== Simulation 2 Start ==============");
        Array<Vector2> posEnd_2 = runSimulation(2);
        System.out.println("========== Simulation 2 End ==============");

        System.out.println("========== Simulation 3 Start ==============");
        Array<Vector2> posEnd_3 = runSimulation(3);
        System.out.println("========== Simulation 3 End ==============");

        for (int i = 0; i < bodyCount; i++) {
            Vector2 bodyPosEnd1 = posEnd_1.get(i);
            Vector2 bodyPosEnd2 = posEnd_2.get(i);
            Vector2 bodyPosEnd3 = posEnd_3.get(i);

            Assertions.assertEquals(bodyPosEnd1.x, bodyPosEnd2.x, "body " + i);
            Assertions.assertEquals(bodyPosEnd1.y, bodyPosEnd2.y, "body " + i);

            Assertions.assertEquals(bodyPosEnd1.x, bodyPosEnd2.x, "body " + i);
            Assertions.assertEquals(bodyPosEnd3.y, bodyPosEnd3.y, "body " + i);
        }
    }

    private Array<Vector2> runSimulation(int simulationId) {
        World world = new World(new Vector2(), false);
        Rectangle[] wallsRect = getRectsAround(AREA_RECT, 2);

        Array<Body> bodiesToCheck = new Array<>();
        //create walls
        for (Rectangle rect : wallsRect) {
            BodyDef build = BodyDefBuilder.get().active(true).type(BodyDef.BodyType.StaticBody).build();
            Body body = world.createBody(build);
            body.createFixture(FixtureDefBuilder.get().rectangleShape(rect).build());
            body.setTransform(rect.x + rect.width / 2, rect.y + rect.height / 2, 0);
        }

        //create bodies
        for (Vector2 size : bodySize) {
            BodyDef build = BodyDefBuilder.get().active(true).type(BodyDef.BodyType.DynamicBody).build();
            Body body = world.createBody(build);
            body.createFixture(FixtureDefBuilder.get().rectangleShape(size.x, size.y).build());

            bodiesToCheck.add(body);
            posStart.add(body.getPosition().cpy());
        }
        simulateInput(world, bodiesToCheck, simulationId);

        Array<Vector2> posEnd = new Array<>();
        for (int i = 0; i < bodyCount; i++) {
            posEnd.add(bodiesToCheck.get(i).getPosition().cpy());
        }
        world.dispose();
        return posEnd;

    }

    private void simulateInput(World world, Array<Body> bodiesToCheck, int simulationId) {
        float logSometimes = 0;

        float SIMULATION_TIME = 0;

        //reset
        for (int i = 0; i < bodyCount; i++) {
            Body body = bodiesToCheck.get(i);
            body.setTransform(posStart.get(i).x, posStart.get(i).y, 0);
            body.setLinearVelocity(0, 0);
            body.setAngularVelocity(0);
            inputsMap.get(i).reset();
        }

        while (SIMULATION_TIME < SIMULATION_DURATION) {
            for (int i = 0; i < bodyCount; i++) {
                Body body = bodiesToCheck.get(i);
                InputVelocity inputVelocity = inputsMap.get(i);
                inputVelocity.update(DT, body);
            }
            world.step(DT, 1, 1);
            SIMULATION_TIME += DT;
            logSometimes += DT;
            if (logSometimes > 10) {
                System.out.println("Simulation " + simulationId + " time=" + SIMULATION_TIME);
                logSometimes = 0;
            }
        }
    }

    private static Rectangle[] getRectsAround(Rectangle rect, float sizeRect) {
        Rectangle[] rects = new Rectangle[4];

        Rectangle rectBot = new Rectangle(rect.x, rect.y - sizeRect, rect.width, sizeRect);
        rects[0] = rectBot;

        Rectangle rectTop = new Rectangle(rect.x, rect.y + rect.height, rect.width, sizeRect);
        rects[1] = rectTop;

        Rectangle rectLeft = new Rectangle(rect.x - sizeRect, rect.y, sizeRect, rect.getHeight());
        rects[2] = rectLeft;

        Rectangle rectRight = new Rectangle(rect.x + rect.getWidth(), rect.y, sizeRect, rect.getHeight());
        rects[3] = rectRight;
        return rects;
    }

    public static Vector2 getRandomPos(Rectangle rect) {
        return new Vector2(MathUtils.random(rect.x, rect.x + rect.width), MathUtils.random(rect.y, rect.y + rect.height));
    }

    private static class InputVelocity {
        int bodyId;
        Array<Vector3> array = new Array<>();
        int index = 0;
        float timeElapsed;

        public InputVelocity(int bodyId) {
            this.bodyId = bodyId;
        }

        public void reset() {
            index = 0;
            timeElapsed = 0;
        }

        public void update(float dt, Body body) {
            if (index < array.size) {
                timeElapsed += dt;

                Vector3 vector3 = array.get(index);
                if (timeElapsed > vector3.z) {
                    body.setTransform(vector3.x, vector3.y, 0);
                    timeElapsed = 0;
                    index++;
                }
            }
        }

        public void add(float x, float y, float time) {
            array.add(new Vector3(x, y, time));
        }
    }
}
