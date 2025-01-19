package com.mentalfrostbyte.jello.gui.unmapped;

import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.managers.impl.account.microsoft.Account;
import com.mentalfrostbyte.jello.managers.impl.account.microsoft.Ban;
import com.mentalfrostbyte.jello.util.ClientColors;
import com.mentalfrostbyte.jello.util.ResourceRegistry;
import com.mentalfrostbyte.jello.util.render.ColorUtils;
import com.mentalfrostbyte.jello.util.render.RenderUtil;
import com.mentalfrostbyte.jello.util.render.Resources;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Class4296 extends AnimatedIconPanelWrap {
   private static String[] field20812;
   private Account field20813 = null;
   private List<Class4348> field20814 = new ArrayList<Class4348>();
   private float field20815 = 0.0F;

   public Class4296(CustomGuiScreen var1, String var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6, false);
   }

   public void method13178(Account var1) {
      this.field20813 = var1;

      for (Class4348 var5 : this.field20814) {
         this.method13234(var5);
      }

      if (var1 != null) {
         List<Ban> var11 = new ArrayList();

         for (Ban var6 : var1.getBans()) {
            var11.add(var6);
         }

         Collections.reverse(var11);
         int var13 = 0;
         int var14 = 90;
         int var7 = 14;

         for (Ban var9 : var11) {
            if (var9.method31736() != null && var9.method31736().getBase64EncodedIconData() != null) {
               Class4348 var10 = new Class4348(
                  this, ((Ban)var11.get(var13)).getServerIP(), 40, 100 + var13 * (var14 + var7), this.widthA - 90, var14, var9
               );
               this.addToList(var10);
               this.field20814.add(var10);
               var13++;
            }
         }

         this.setHeightA(var13 * (var14 + var7) + 116);
      }
   }

   @Override
   public void draw(float var1) {
      this.method13225();
      this.field20815 = (float)((double)this.field20815 + (this.isVisible() ? 0.33 : -0.33));
      this.field20815 = Math.min(1.0F, Math.max(0.0F, this.field20815));
      if (this.field20813 == null) {
         int var4 = this.widthA - 30;
         int var5 = this.xA + 5;
         RenderUtil.drawImage(
            (float)var5,
            (float)((Minecraft.getInstance().getMainWindow().getHeight() - var4 * 342 / 460) / 2 - 60),
            (float)var4,
            (float)(var4 * 342 / 460),
            Resources.imgPNG
         );
      }

      if (this.field20813 != null) {
         int var7 = ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), 0.7F);
         RenderUtil.drawString(
            ResourceRegistry.JelloLightFont36,
            (float)(this.xA + (this.widthA - ResourceRegistry.JelloLightFont36.getWidth(this.field20813.getKnownName())) / 2),
            (float)this.yA,
            this.field20813.getKnownName(),
            var7
         );
         super.draw(var1);
      }
   }
}
