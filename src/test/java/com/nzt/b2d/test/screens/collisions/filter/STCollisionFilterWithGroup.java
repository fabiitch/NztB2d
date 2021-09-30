package com.nzt.b2d.test.screens.collisions.filter;

import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

/**
 * Function Box2D
 * bool collide = (filterA.maskBits & filterB.categoryBits) != 0 &&
 * (filterA.categoryBits & filterB.maskBits) != 0;
 **/
public class STCollisionFilterWithGroup extends CollisionFilterTestScreen {
    public STCollisionFilterWithGroup(FastTesterMain main) {
        super(main);
    }

    @Override
    public String getTestExplication() {
        return "TestFilter avec group";
    }


    final short CAT_1 = 1 << 0;
    final int CAT_2 = 1 << 1;
    final int CAT_3 = 1 << 2;
    final int CAT_4 = 1 << 3;

    @Override
    protected void setFilters() {
        fixture1.setFilter(0, 0, 1);
        fixture2.setFilter(0, 0, 0);
        fixture3.setFilter(0, 0, -1);
        fixture4.setFilter(0, 0, -2);
    }
}
