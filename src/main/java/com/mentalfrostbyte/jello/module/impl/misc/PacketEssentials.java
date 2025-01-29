package com.mentalfrostbyte.jello.module.impl.misc;

import com.mentalfrostbyte.jello.event.impl.game.network.EventReceivePacket;
import com.mentalfrostbyte.jello.module.InDevelopment;
import com.mentalfrostbyte.jello.module.Module;
import com.mentalfrostbyte.jello.module.ModuleCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.network.play.server.*;
import team.sdhq.eventBus.annotations.EventTarget;

@InDevelopment
public class PacketEssentials extends Module {
    public PacketEssentials() {
        super(ModuleCategory.MISC, "PacketEssentials", "Ignores all types of junk cosmetic packets to improve fps");
    }

    @EventTarget
    public void onReceivePacket(EventReceivePacket var1) {
        if (this.isEnabled()) {
            if (!(var1.getPacket() instanceof SSpawnObjectPacket)) {
                if (!(var1.getPacket() instanceof SSpawnMobPacket)) {
                    if (!(var1.getPacket() instanceof SSpawnParticlePacket)) {
                        if (!(var1.getPacket() instanceof SUpdateBossInfoPacket)) {
                            if (!(var1.getPacket() instanceof SScoreboardObjectivePacket)) {
                                if (var1.getPacket() instanceof SEntityMetadataPacket) {
                                    var1.setCancelled(true);
                                }
                            } else {
                                var1.setCancelled(true);
                            }
                        } else {
                            var1.setCancelled(true);
                        }
                    } else {
                        SSpawnParticlePacket var4 = (SSpawnParticlePacket) var1.getPacket();
                        var1.setCancelled(true);
                    }
                } else {
                    SSpawnMobPacket var5 = (SSpawnMobPacket) var1.getPacket();
                    if (var5.getEntityType() == 1) {
                        var1.setCancelled(true);
                    }
                }
            } else {
                SSpawnObjectPacket var6 = (SSpawnObjectPacket) var1.getPacket();
                if (var6.getType() == EntityType.ARMOR_STAND) {
                    var1.setCancelled(true);
                }

                if (var6.getType() == EntityType.GIANT) {
                    var1.setCancelled(true);
                }
            }
        }
    }
}
