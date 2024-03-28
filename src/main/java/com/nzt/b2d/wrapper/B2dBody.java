package com.nzt.b2d.wrapper;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventContainer;
import com.nzt.b2d.events.type.properties.B2dDestroyBodyEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2dBody {

    private int id;
    private Body body;
    private B2dEventContainer eventContainer = new B2dEventContainer();

    private boolean destroyed;

    public void addEvent(B2dBaseEvent event) {
        eventContainer.addEvent(event);
    }

    public void addEvent(B2dDestroyBodyEvent destroyEvent) {
        eventContainer.addEvent(destroyEvent);
    }
}
