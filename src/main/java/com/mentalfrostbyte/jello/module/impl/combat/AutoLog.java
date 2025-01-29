package com.mentalfrostbyte.jello.module.impl.combat;

import com.mentalfrostbyte.jello.event.impl.player.EventPlayerTick;
import com.mentalfrostbyte.jello.module.Module;
import com.mentalfrostbyte.jello.module.ModuleCategory;

import com.mentalfrostbyte.jello.module.settings.impl.BooleanSetting;
import com.mentalfrostbyte.jello.module.settings.impl.NumberSetting;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import team.sdhq.eventBus.annotations.EventTarget;

public class AutoLog extends Module {
    public AutoLog() {
        super(ModuleCategory.COMBAT, "AutoLog", "Automatically logs out");
        this.registerSetting(new NumberSetting<Float>("Min Health", "Minimum health before it logs you out", 2.5F, Float.class, 0.0F, 10.0F, 0.01F));
        this.registerSetting(new BooleanSetting("No Totems", "Logs out when you have no totems in inventory", false));
        this.registerSetting(new BooleanSetting("One Time Use", "Disables the mod every time it saves you.", true));
    }

    @EventTarget
    public void method16498(EventPlayerTick var1) {
        if (this.isEnabled() && mc.player.ticksExisted > 10 && mc.getIntegratedServer() == null && mc.getCurrentServerData() != null) {
            float var4 = mc.player.getHealth() / mc.player.getMaxHealth() * 10.0F;
            if (var4 < this.getNumberValueBySettingName("Min Health") || Items.TOTEM_OF_UNDYING == null) {
                boolean var5 = this.getBooleanValueFromSettingName("One Time Use");
                mc.world.sendQuittingDisconnectingPacket();
                mc.unloadWorld();
                mc.displayGuiScreen(
                        new DisconnectedScreen(
                                new MultiplayerScreen(new MainMenuScreen()),
                                new TranslationTextComponent("disconnect.lost"),
                                new StringTextComponent(
                                        "AutoLog disconnected you. " + (!var5 ? "Disable it in a singleplayer world to reconnect." : "The mod is now disabled for you to reconnect.")
                                )
                        )
                );
            }
        }
    }
}

