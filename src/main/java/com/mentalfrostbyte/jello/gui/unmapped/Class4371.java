package com.mentalfrostbyte.jello.gui.unmapped;

import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.util.client.ClientColors;
import com.mentalfrostbyte.jello.util.render.ColorUtils;
import com.mentalfrostbyte.jello.util.game.render.RenderUtil;

public class Class4371 extends UIBase {
   public int field21365;

   public Class4371(CustomGuiScreen var1, String var2, int var3, int var4, int var5, int var6, int var7) {
      super(var1, var2, var3, var4, var5, var6, false);
      this.field21365 = var7;
   }

   @Override
   public void draw(float partialTicks) {
      RenderUtil.drawCircle(
         (float)this.xA + (float)this.widthA / 2.0F,
         (float)this.yA + (float)this.widthA / 2.0F,
         (float)this.widthA,
         ColorUtils.applyAlpha(ColorUtils.shiftTowardsOther(this.field21365, ClientColors.DEEP_TEAL.getColor(), 0.8F), partialTicks)
      );
      RenderUtil.drawCircle(
         (float)this.xA + (float)this.widthA / 2.0F,
         (float)this.yA + (float)this.widthA / 2.0F,
         (float)(this.widthA - 2),
              ColorUtils.applyAlpha(this.field21365, partialTicks)
      );
      if (this.method13212()) {
         RenderUtil.drawCircle(
            (float)this.xA + (float)this.widthA / 2.0F,
            (float)this.yA + (float)this.widthA / 2.0F,
            (float)(this.widthA - 2),
                 ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), partialTicks * 0.2F)
         );
      }

      super.draw(partialTicks);
   }
}
