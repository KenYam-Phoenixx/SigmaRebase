package com.mentalfrostbyte.jello.gui.unmapped;

import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.gui.impl.jello.ingame.clickgui.ClickGuiScreen;
import net.minecraft.client.Minecraft;

public class ClickGUIBlurOverlay extends AnimatedIconPanelWrap {
    public final ClickGuiScreen field21278;

   public ClickGUIBlurOverlay(ClickGuiScreen var1, CustomGuiScreen var2, String var3) {
      super(var2, var3, 0, 0, Minecraft.getInstance().getMainWindow().getWidth(), Minecraft.getInstance().getMainWindow().getHeight(), false);
      this.field21278 = var1;
   }

   @Override
   public void onClick3(int mouseX, int mouseY, int mouseButton) {
      super.onClick3(mouseX, mouseY, mouseButton);
   }
}
