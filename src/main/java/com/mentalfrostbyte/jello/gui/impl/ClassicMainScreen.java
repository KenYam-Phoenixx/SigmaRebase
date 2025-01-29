package com.mentalfrostbyte.jello.gui.impl;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.gui.base.Animation;
import com.mentalfrostbyte.jello.gui.base.Direction;
import com.mentalfrostbyte.jello.gui.base.QuadraticEasing;
import com.mentalfrostbyte.jello.gui.base.Screen;
import com.mentalfrostbyte.jello.gui.unmapped.AnimatedIconPanelWrap;
import com.mentalfrostbyte.jello.gui.unmapped.Class4365;
import com.mentalfrostbyte.jello.gui.unmapped.ClassicParticleEngine;
import com.mentalfrostbyte.jello.gui.unmapped.UITextDisplay;
import com.mentalfrostbyte.jello.util.ClientColors;
import com.mentalfrostbyte.jello.util.ColorHelper;
import com.mentalfrostbyte.jello.util.ResourceRegistry;
import com.mentalfrostbyte.jello.util.render.ColorUtils;
import com.mentalfrostbyte.jello.util.render.RenderUtil;
import com.mentalfrostbyte.jello.util.render.Resources;
import org.newdawn.slick.TrueTypeFont;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;

public class ClassicMainScreen extends Screen {
    private static final long field21106 = System.nanoTime();
    public final AnimatedIconPanelWrap field21094;
    public final AnimatedIconPanelWrap field21095;
    private final int field21096 = 0;
    private final int field21097 = 0;
    private final boolean field21098 = true;
    private Class4365 field21099;
    private Animation field21100 = new Animation(325, 325);
    private final Animation field21101 = new Animation(800, 800);
    private final ClassicParticleEngine field21102;
    private final ClassicTitleScreen field21103;
    private float field21104;
    private float field21105;

    public ClassicMainScreen() {
        super("Main Screen");
        this.setListening(false);
        this.field21100 = new Animation(175, 325);
        this.field21100.changeDirection(Direction.FORWARDS);
        this.field21101.changeDirection(Direction.BACKWARDS);
        TrueTypeFont var9 = Resources.regular20;
        String var11 = "© Sigma Prod";
        StringBuilder var10000 = new StringBuilder().append("Sigma ");
        Client.getInstance();
        String var12 = var10000.append(Client.VERSION).append(" for Minecraft 1.8 to ").append("1.16.4").toString();
        this.addToList(this.field21102 = new ClassicParticleEngine(this, "particles"));
        int var13 = 480;
        int var14 = 480;
        this.addToList(this.field21103 = new ClassicTitleScreen(this, "group", (this.getWidthA() - var13) / 2,
                this.getHeightA() / 2 - 230, var13, var14));
        this.addToList(
                this.field21095 = new UITextDisplay(
                        this, "Copyright", 10, 8, var9.getWidth(var11), 140,
                        new ColorHelper(ClientColors.LIGHT_GREYISH_BLUE.getColor()), var11,
                        ResourceRegistry.JelloLightFont18));
        ColorHelper var15 = new ColorHelper(ColorUtils.applyAlpha(ClientColors.LIGHT_GREYISH_BLUE.getColor(), 0.5F));
        var15.method19410(ColorUtils.applyAlpha(ClientColors.LIGHT_GREYISH_BLUE.getColor(), 0.5F));
        ArrayList<String> var16 = new ArrayList<>();
        var16.add("LeakedPvP");
        var16.add("Omikron");
        var16.add("Away");
        var16.add("Mark");
        var16.add("DataModel");
        Collections.shuffle(var16);
        String var17 = "by " + var16.get(0) + ", " + var16.get(1);
        this.addToList(
                new UITextDisplay(this, "names", 130, 9, var9.getWidth(var11), 140, var15, var17, Resources.regular17));
        this.addToList(
                this.field21094 = new UITextDisplay(
                        this,
                        "Version",
                        this.getWidthA() - var9.getWidth(var12) - 9,
                        this.getHeightA() - 31,
                        114,
                        140,
                        new ColorHelper(ClientColors.LIGHT_GREYISH_BLUE.getColor()),
                        var12,
                        var9));
        this.addToList(new UITextDisplay(this, "Hello", 10, this.getHeightA() - 55, 114, 140,
                new ColorHelper(ClientColors.LIGHT_GREYISH_BLUE.getColor()), "Hello,", var9));
        this.addToList(
                new UITextDisplay(
                        this, "Latest", 10, this.getHeightA() - 31, 114, 140,
                        new ColorHelper(ClientColors.LIGHT_GREYISH_BLUE.getColor()), "You are using the latest version",
                        var9));
        this.field21104 = (float) (this.getWidthA() / 2);
        this.field21105 = (float) (this.getHeightA() / 2);
    }

    private int method13432(int var1) {
        int var6 = 4;
        int var7 = -6;
        int var8 = 122 * var6 + var6 * var7;
        if (var1 < var6) {
            return this.getWidthA() / 2 - var8 / 2 + var1 * 122 + var1 * var7;
        } else {
            var1 -= var6;
            var6 = 3;
            var7 = 6;
            var8 = 122 * var6 + var6 * var7;
            return this.getWidthA() / 2 - var8 / 2 + var1 * 122 + var1 * var7;
        }
    }

    private int method13433() {
        return this.getHeightA() / 2 - 100;
    }

    public void method13434(net.minecraft.client.gui.screen.Screen var1) {
        MinecraftClient.getInstance().displayGuiScreen(var1);
        this.method13436();
    }

    public void method13435(Screen var1) {
        Client.getInstance().guiManager.handleScreen(var1);
        this.method13436();
    }

    public void method13436() {
    }

    @Override
    public void updatePanelDimensions(int newHeight, int newWidth) {
        float var5 = (float) newHeight - this.field21104;
        float var6 = (float) newWidth - this.field21105;
        this.field21104 += var5 * 0.055F;
        this.field21105 += var6 * 0.055F;
        super.updatePanelDimensions(newHeight, newWidth);
    }

    @Override
    public void draw(float partialTicks) {
        int var4 = Math
                .round((1.0F - QuadraticEasing.easeOutQuad(this.field21100.calcPercent(), 0.0F, 1.0F, 1.0F)) * 5.0F);
        this.drawBackground(var4);
        this.method13225();
        GL11.glPushMatrix();
        GL11.glTranslated(
                (int) ((float) (-this.getWidthA() / 200) + this.field21104 / 200.0F),
                (int) ((float) (-this.getHeightA() / 100) + this.field21105 / 100.0F) - var4,
                0.0);
        RenderUtil.drawImage(-10.0F, -10.0F, (float) (this.getWidthA() + 20), (float) (this.getHeightA() + 20),
                Resources.mainmenubackground);
        GL11.glPopMatrix();
        this.field21103
                .draw(
                        (int) ((float) (-this.getWidthA() / 40) + this.field21104 / 40.0F),
                        (int) ((float) (-this.getHeightA() / 40) + this.field21105 / 40.0F) + var4);
        this.field21102
                .draw((int) ((float) (-this.getWidthA() / 12) + this.field21104 / 12.0F),
                        (int) ((float) (-this.getHeightA() / 12) + this.field21105 / 12.0F));
        super.draw(partialTicks);
    }
}
