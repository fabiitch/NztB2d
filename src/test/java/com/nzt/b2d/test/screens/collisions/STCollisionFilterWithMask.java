package com.nzt.b2d.test.screens.collisions;

import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "collisions")
public class STCollisionFilterWithMask extends STCollisionTestScreen {
    public STCollisionFilterWithMask(FastTesterMain main) {
        super(main);
    }

    final short CAT_1 = 1 << 0;
    final short CAT_2 = 1 << 1;
    final short CAT_3 = 1 << 2;
    final short CAT_4 = 1 << 4;

    final short MASK_1 = 1 << 1;
    final short MASK_2 = 1 << 1;
    final short MASK_3 = 1 << 2;
    final short MASK_4 = 1 << 4;

    @Override
    public String getTestExplication() {
        return "TestFilter avec mask";
    }

    /**
     * bool collide = (filterA.maskBits & filterB.categoryBits) != 0 &&
     * (filterA.categoryBits & filterB.maskBits) != 0;
     **/
    @Override
    protected void setFilters() {
        fixture1.setFilter(CAT_1, MASK_1, 0);
        fixture2.setFilter(CAT_2, MASK_2, 0);
        fixture3.setFilter(CAT_3, MASK_3, 0);
        fixture4.setFilter(CAT_4, MASK_4, -10);
    }
}
