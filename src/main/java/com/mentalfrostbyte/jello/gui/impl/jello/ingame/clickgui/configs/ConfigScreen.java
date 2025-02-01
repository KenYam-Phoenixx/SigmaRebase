package com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.configs;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.gui.base.Animation;
import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.gui.base.Direction;
import com.mentalfrostbyte.jello.gui.base.QuadraticEasing;
import com.mentalfrostbyte.jello.gui.impl.jello.buttons.ScrollableContentPanel;
import com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.ClickGuiScreen;
import com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.configs.groups.ConfigGroup;
import com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.configs.groups.ProfileGroup;
import com.mentalfrostbyte.jello.gui.unmapped.*;
import com.mentalfrostbyte.jello.managers.ProfileManager;
import com.mentalfrostbyte.jello.managers.util.profile.Configuration;
import com.mentalfrostbyte.jello.util.ClientColors;
import com.mentalfrostbyte.jello.util.ColorHelper;
import com.mentalfrostbyte.jello.util.MathUtils;
import com.mentalfrostbyte.jello.util.ResourceRegistry;
import com.mentalfrostbyte.jello.util.render.ColorUtils;
import com.mentalfrostbyte.jello.util.render.RenderUtil;
import totalcross.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConfigScreen extends UIBase {
    private List<Button> field21297 = new ArrayList<Button>();
    public final Animation field21298;
    public ScrollableContentPanel profileScrollView;
    public ConfigGroup field21300;
    private List<ProfileGroup> field21301 = new ArrayList<ProfileGroup>();

    public ConfigScreen(CustomGuiScreen var1, String var2, int var3, int var4) {
        super(var1, var2, var3 - 250, var4 - 500, 250, 500, ColorHelper.field27961, false);
        this.field21298 = new Animation(300, 100);
        this.method13292(true);
        this.setListening(false);
        UIButton addButton;
        this.addToList(
                addButton = new UIButton(
                        this, "addButton", this.widthA - 55, 0, ResourceRegistry.JelloLightFont25.getWidth("Add"), 69, ColorHelper.field27961, "+", ResourceRegistry.JelloLightFont25
                )
        );
        addButton.doThis((var1x, var2x) -> this.field21300.method13119(true));
        this.addToList(this.field21300 = new ConfigGroup(this, "profile", 0, 69, this.widthA, 200));
        this.field21300.method13292(true);
        this.method13615();
    }

    public void method13610() {
        Client.getInstance();
        ProfileManager var3 = Client.getInstance().moduleManager.getConfigurationManager();
        Configuration var4 = var3.getCurrentConfig();
        int var5 = 1;

        while (var3.getConfigByCaseInsensitiveName(var4.getName + " Copy " + var5)) {
            var5++;
        }

        var3.saveConfig(var4.method22987(var4.getName + " Copy " + var5));
        this.runThisOnDimensionUpdate(() -> this.method13615());
        this.field21300.method13119(false);
    }

    public void method13611(Configuration var1) {
        Client.getInstance();
        ProfileManager var4 = Client.getInstance().moduleManager.getConfigurationManager();
        Configuration var5 = var4.getCurrentConfig();
        int var6 = 1;

        while (var4.getConfigByCaseInsensitiveName(var1.getName + " " + var6)) {
            var6++;
        }

        var4.saveConfig(var1.method22987(var1.getName + " " + var6));
        this.runThisOnDimensionUpdate(() -> this.method13615());
        this.field21300.method13119(false);
    }

    public void method13612() {
        Client.getInstance();
        ProfileManager var3 = Client.getInstance().moduleManager.getConfigurationManager();
        int var4 = 1;

        while (var3.getConfigByCaseInsensitiveName("New Profile " + var4)) {
            var4++;
        }

        var3.saveConfig(new Configuration("New Profile " + var4, new JSONObject()));
        this.runThisOnDimensionUpdate(this::method13615);
        this.field21300.method13119(false);
    }

    public void method13613() {
        this.field21300.field20703.changeDirection(Direction.BACKWARDS);
        if (this.field21298.getDirection() != Direction.BACKWARDS) {
            this.field21298.changeDirection(Direction.BACKWARDS);
        }
    }

    public boolean method13614() {
        return this.field21298.getDirection() == Direction.BACKWARDS && this.field21298.calcPercent() == 0.0F;
    }

    @Override
    public void updatePanelDimensions(int newHeight, int newWidth) {
        if (newWidth > this.field21300.method13272() + this.field21300.getHeightA()) {
            this.field21300.method13119(false);
        }

        super.updatePanelDimensions(newHeight, newWidth);
    }

    public void method13615() {
        int var3 = 0;
        if (this.profileScrollView != null) {
            var3 = this.profileScrollView.method13513();
            this.method13236(this.profileScrollView);
        }

        this.addToList(this.profileScrollView = new ScrollableContentPanel(this, "profileScrollView", 10, 80, this.widthA - 20, this.heightA - 80 - 10));
        this.profileScrollView.method13512(var3);
        this.field21301.clear();
        int var4 = 0;
        int var5 = 70;

        for (Configuration var7 : Client.getInstance().moduleManager.getConfigurationManager().getAllConfigs()) {
            ProfileGroup var8 = new ProfileGroup(this, "profile" + var4, 0, var5 * var4, this.profileScrollView.getWidthA(), var5, var7, var4);
            this.profileScrollView.addToList(var8);
            this.field21301.add(var8);
            var4++;
        }

        ClickGuiScreen var9 = (ClickGuiScreen) this.getParent();
        var9.method13315();
    }

    public void method13616() {
        int var3 = 0;

        for (ProfileGroup var5 : this.field21301) {
            var5.setYA(var3);
            var3 += var5.getHeightA();
        }
    }

    @Override
    public void draw(float partialTicks) {
        partialTicks = this.field21298.calcPercent();
        this.method13616();
        float var4 = MathUtils.lerp(partialTicks, 0.37, 1.48, 0.17, 0.99);
        if (this.field21298.getDirection() == Direction.BACKWARDS) {
            var4 = MathUtils.lerp(partialTicks, 0.38, 0.73, 0.0, 1.0);
        }

        this.method13279(0.8F + var4 * 0.2F, 0.8F + var4 * 0.2F);
        this.drawBackground((int) ((float) this.widthA * 0.25F * (1.0F - var4)));
        this.method13284((int) ((float) this.widthA * 0.14F * (1.0F - var4)));
        super.method13224();
        super.method13225();
        int var5 = 10;
        int var6 = ColorUtils.applyAlpha(-723724, QuadraticEasing.easeOutQuad(partialTicks, 0.0F, 1.0F, 1.0F));
        RenderUtil.drawRoundedRect(
                (float) (this.xA + var5 / 2),
                (float) (this.yA + var5 / 2),
                (float) (this.widthA - var5),
                (float) (this.heightA - var5),
                35.0F,
                partialTicks
        );
        RenderUtil.drawRoundedRect(
                (float) (this.xA + var5 / 2),
                (float) (this.yA + var5 / 2),
                (float) (this.xA - var5 / 2 + this.widthA),
                (float) (this.yA - var5 / 2 + this.heightA),
                ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), partialTicks * 0.25F)
        );
        RenderUtil.drawRoundedRect((float) this.xA, (float) this.yA, (float) this.widthA, (float) this.heightA, (float) var5, var6);
        float var7 = 0.9F + (1.0F - MathUtils.lerp(this.field21300.field20703.calcPercent(), 0.0, 0.96, 0.69, 0.99)) * 0.1F;
        if (this.field21300.field20703.getDirection() == Direction.BACKWARDS) {
            var7 = 0.9F + (1.0F - MathUtils.lerp(this.field21300.field20703.calcPercent(), 0.61, 0.01, 0.87, 0.16)) * 0.1F;
        }

        this.profileScrollView.method13279(var7, var7);
        RenderUtil.drawString(
                ResourceRegistry.JelloLightFont25,
                (float) (this.xA + 25),
                (float) (this.yA + 20),
                "Profiles",
                ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), 0.8F * partialTicks)
        );
        RenderUtil.drawRoundedRect(
                (float) (this.xA + 25),
                (float) (this.yA + 69),
                (float) (this.xA + this.widthA - 25),
                (float) (this.yA + 70),
                ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), 0.05F * partialTicks)
        );
        super.draw(partialTicks);
    }
}
