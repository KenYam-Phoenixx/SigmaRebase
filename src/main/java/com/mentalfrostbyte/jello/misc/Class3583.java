package com.mentalfrostbyte.jello.misc;

import com.mentalfrostbyte.jello.util.game.world.BlockUtil;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Comparator;

public final class Class3583 implements Comparator<PlayerEntity> {
    private static String[] field19525;

    public int compare(PlayerEntity var1, PlayerEntity var2) {
        float var5 = BlockUtil.mc.player.getDistance(var1);
        float var6 = BlockUtil.mc.player.getDistance(var2);
        if (!(var5 - var6 < 0.0F)) {
            return var5 - var6 != 0.0F ? -1 : 0;
        } else {
            return 1;
        }
    }
}
