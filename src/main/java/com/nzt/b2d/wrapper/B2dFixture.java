package com.nzt.b2d.wrapper;

import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2dFixture {

    private int id;
    private B2dBody b2dBody;

    private Fixture fixture;
    private boolean destroyed = false;

}
