package com.mentalfrostbyte.jello.events.impl;

import com.mentalfrostbyte.jello.events.CancellableEvent;
import net.minecraft.entity.Entity;

public class EventRenderNameTag extends CancellableEvent {
    private final Entity field21580;

    public EventRenderNameTag(Entity var1) {
        this.field21580 = var1;
    }

    public Entity getEntity() {
        return this.field21580;
    }
}

