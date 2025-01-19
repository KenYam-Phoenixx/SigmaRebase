package com.mentalfrostbyte.jello.event.impl;

import com.mentalfrostbyte.jello.event.CancellableEvent;
import net.minecraft.network.IPacket;

public class ReceivePacketEvent extends CancellableEvent {
    private IPacket<?> packet;

    public ReceivePacketEvent(IPacket<?> var1) {
        this.packet = var1;
    }

    public IPacket<?> getPacket() {
        return this.packet;
    }

    public void setPacket(IPacket packet) {
        this.packet = packet;
    }
}

