package com.mentalfrostbyte.jello.gui.impl;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.gui.base.Screen;
import com.mentalfrostbyte.jello.gui.unmapped.Class4300;
import com.mentalfrostbyte.jello.gui.unmapped.SigmaClassicTextBox;
import com.mentalfrostbyte.jello.managers.AccountManager;
import com.mentalfrostbyte.jello.managers.util.account.microsoft.Account;
import com.mentalfrostbyte.jello.util.ClientColors;
import com.mentalfrostbyte.jello.util.ResourceRegistry;
import com.mentalfrostbyte.jello.util.render.ColorUtils;
import com.mentalfrostbyte.jello.util.render.RenderUtil;
import com.mentalfrostbyte.jello.util.render.Resources;
import com.mentalfrostbyte.jello.util.unmapped.Class2218;

public class ClassicEditAlt extends Screen {
   public SigmaClassicTextBox field21027;
   public SigmaClassicTextBox field21028;
   public Class4300 field21029;
   public Class4300 field21030;
   public AccountManager field21031 = Client.getInstance().accountManager;
   private String field21032 = "§7Waiting...";

   public ClassicEditAlt(Account var1) {
      super("Alt Manager");

      this.setListening(false);
      int var4 = 400;
      int var5 = 114;
      int var6 = (this.getWidthA() - var4) / 2;
      this.addToList(this.field21027 = new SigmaClassicTextBox(this, "username", var6, var5, var4, 45, SigmaClassicTextBox.field20741, "", "New name", ResourceRegistry.DefaultClientFont));
      var5 += 80;
      this.addToList(this.field21028 = new SigmaClassicTextBox(this, "password", var6, var5, var4, 45, SigmaClassicTextBox.field20741, "", "New password", ResourceRegistry.DefaultClientFont));
      var5 += 190;
      this.addToList(this.field21029 = new Class4300(this, "edit", var6, var5, var4, 40, "Edit", ClientColors.MID_GREY.getColor()));
      var5 += 50;
      this.addToList(this.field21030 = new Class4300(this, "back", var6, var5, var4, 40, "Cancel", ClientColors.MID_GREY.getColor()));
      this.field21028.method13155(true);
      this.field21028.method13147("*");
      this.field21029.doThis((var2, var3) -> {
         if (this.field21027.getTypedText().length() > 0) {
            if (!this.field21027.getTypedText().equals(var1.getEmail())) {
               var1.setName(this.field21027.getTypedText());
            }

            var1.setEmail(this.field21027.getTypedText());
         }

         var1.setPassword(this.field21028.getTypedText());
         this.field21032 = "Edited!";
      });
      this.field21030.doThis((var0, var1x) -> Client.getInstance().guiManager.handleScreen(new SigmaClassicAltManager()));
   }

   @Override
   public void draw(float partialTicks) {
      RenderUtil.drawImage(0.0F, 0.0F, (float)this.getWidthA(), (float)this.getHeightA(), Resources.mainmenubackground);
      RenderUtil.drawRoundedRect(0.0F, 0.0F, (float)this.getWidthA(), (float)this.getHeightA(), ColorUtils.applyAlpha(ClientColors.PALE_RED.getColor(), 0.1F));
      RenderUtil.drawRoundedRect(0.0F, 0.0F, (float)this.getWidthA(), (float)this.getHeightA(), ColorUtils.applyAlpha(ClientColors.DEEP_TEAL.getColor(), 0.95F));
      RenderUtil.drawString(
         ResourceRegistry.DefaultClientFont, (float)(this.getWidthA() / 2), 20.0F, "Edit Alt", ClientColors.LIGHT_GREYISH_BLUE.getColor(), Class2218.field14492, Class2218.field14488
      );
      RenderUtil.drawString(
         ResourceRegistry.DefaultClientFont,
         (float)(this.getWidthA() / 2),
         40.0F,
         this.field21032,
         ClientColors.LIGHT_GREYISH_BLUE.getColor(),
         Class2218.field14492,
         Class2218.field14488,
         true
      );
      super.draw(partialTicks);
   }

   @Override
   public void keyPressed(int keyCode) {
      super.keyPressed(keyCode);
      if (keyCode == 256) {
         Client.getInstance().guiManager.handleScreen(new SigmaClassicAltManager());
      }
   }
}
