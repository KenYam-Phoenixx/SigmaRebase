package com.mentalfrostbyte.jello.gui.unmapped;

import com.mentalfrostbyte.jello.gui.base.CustomGuiScreen;
import com.mentalfrostbyte.jello.gui.impl.JelloKeyboardScreen;

public class Class543 implements Runnable {
   private static String[] field2602;
   public final JelloKeyboardScreen field2603;
   public final JelloKeyboardScreen field2604;

   public Class543(JelloKeyboardScreen var1, JelloKeyboardScreen var2) {
      this.field2604 = var1;
      this.field2603 = var2;
   }

   @Override
   public void run() {
      for (CustomGuiScreen var4 : this.field2603.getChildren()) {
         if (var4 instanceof Class4375) {
            Class4375 var5 = (Class4375)var4;
            var5.method13712();
            this.field2604.field20957.method13104();
            var5.method13292(true);
            var5.method13242();
            this.field2603.method13234(this.field2604.field20960);
         }
      }
   }
}
