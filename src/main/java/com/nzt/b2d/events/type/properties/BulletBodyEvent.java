package com.nzt.b2d.events.type.properties;

import com.badlogic.gdx.physics.box2d.Body;
import com.nzt.b2d.events.B2dBaseEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulletBodyEvent extends B2dBaseEvent<BulletBodyEvent> {

    private boolean bullet;

    public BulletBodyEvent() {
        super(B2dEventsEnum.Bullet);
    }

    @Override
    public boolean canConcat(BulletBodyEvent event) {
        return true;
    }

    @Override
    protected void concat(BulletBodyEvent event) {
        this.bullet = event.bullet;
    }

    @Override
    public void reset() {
        bullet = false;
    }

    @Override
    public void apply(Body body) {
        body.setBullet(bullet);
    }

}
