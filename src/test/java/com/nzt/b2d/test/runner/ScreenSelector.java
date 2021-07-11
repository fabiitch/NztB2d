package com.nzt.b2d.test.runner;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.nzt.gdx.main.AbstractMain;
import com.nzt.gdx.screen.AbstractScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Set;

public class ScreenSelector extends TestScreen {
    private Array<Class> testsScreenArrays;
    private Table table;

    public ScreenSelector(FastTesterMain main) {
        super(main);
        Reflections reflections = new Reflections("com.nzt.b2D.screens");

        Set<Class<?>> allTestScreens = reflections.getTypesAnnotatedWith(ScreenTest.class);

        testsScreenArrays = new Array<>();
        int i = 0;
        for (Class<?> st : allTestScreens) {
            if (Modifier.isAbstract(st.getModifiers()))
                continue;
            testsScreenArrays.add(st);
            TextButton textButton = new TextButton(st.getSimpleName(), skin);
            textButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    changeScreenTest(st);
                }
            });
            table.add(textButton).width(nzStage.getWidth() / 4);
            if (i == 3) {
                i = 0;
                table.row();
            } else {
                i++;
            }
        }
    }

    public void changeScreenTest(Class screenClass) {
        try {
            Constructor constructor = screenClass.getConstructor();
            Object newInstance = constructor.newInstance();
            main.screenManager.setScreen((AbstractScreen<AbstractMain>) newInstance);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new GdxRuntimeException("Cant create screen" + screenClass.getSimpleName());
        }
    }

    @Override
    public String getExplication() {
        return "Choose your screen";
    }

    @Override
    public void renderTestScreen(float dt) {

    }

    @Override
    public void disposeTestScreen() {

    }
}
