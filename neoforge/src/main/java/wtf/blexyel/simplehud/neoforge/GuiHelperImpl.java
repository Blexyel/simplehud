package wtf.blexyel.simplehud.neoforge;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class GuiHelperImpl {
  public static void drawString(
      GuiGraphics context, Font font, String text, int x, int y, int color, boolean shadow) {
    try {
      var method =
          GuiGraphics.class.getMethod(
              "drawString",
              Font.class,
              String.class,
              int.class,
              int.class,
              int.class,
              boolean.class);
      method.invoke(context, font, text, x, y, color, shadow);
    } catch (Throwable t) {
      System.err.println("[SimpleHUD/NeoForge] drawString compatibility issue:");
      t.printStackTrace();
    }
  }
}
