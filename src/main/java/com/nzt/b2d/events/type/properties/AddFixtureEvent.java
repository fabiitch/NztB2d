package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFixtureEvent extends B2dEvent<AddFixtureEvent> {

    private FixtureDef fixtureDef;

    public AddFixtureEvent() {
        super(B2dEventsEnum.FixtureAdd);
    }

    @Override
    protected boolean canConcat(AddFixtureEvent event) {
        return false;
    }

    @Override
    protected void concat(AddFixtureEvent event) {

    }
    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.createFixture(fixtureDef);
    }

    @Override
    public void reset() {
        fixtureDef = null;
    }

}
