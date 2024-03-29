package com.nzt.b2d.events.type.properties;

import com.nzt.b2d.events.B2dEvent;
import com.nzt.b2d.events.B2dEventsEnum;
import com.nzt.b2d.wrapper.B2dBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepingAllowedEvent extends B2dEvent<SleepingAllowedEvent> {

    private boolean sleepingAllowed;

    public SleepingAllowedEvent() {
        super(B2dEventsEnum.SleepingAllowed);
    }

    @Override
    protected boolean canConcat(SleepingAllowedEvent event) {
        return true;
    }

    @Override
    protected void concat(SleepingAllowedEvent event) {
        this.sleepingAllowed = event.sleepingAllowed;
    }

    @Override
    public void apply(B2dBody b2dBody) {
        b2dBody.getBody().setSleepingAllowed(sleepingAllowed);
    }

    @Override
    public void reset() {

    }
}
