package com.nzt.b2d.test;

import com.nzt.b2d.test.screens.systems.STDebugDisplaySystemTest;
import com.nzt.gdx.test.utils.archi.mains.mains.SingleScreenTestMain;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztB2dTestStarter {
    private static final Class screenTestClass = STDebugDisplaySystemTest.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztB2dTestListStarter.startLwjgl3(new SingleScreenTestMain(screenTestClass), witdh, height);
    }
}
