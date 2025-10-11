package wtf.blexyel.simplehud;

import dev.architectury.platform.Platform;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class RenderHelper {
  public static void drawString(
      GuiGraphics context, Font font, String text, int x, int y, int color, boolean shadow) {
    try {
      var method =
          GuiGraphics.class.getMethod(
              Platform.isFabric() && !Platform.isDevelopmentEnvironment()
                  ? "method_51433"
                  : "drawString",
              Font.class,
              String.class,
              int.class,
              int.class,
              int.class,
              boolean.class);
      method.invoke(context, font, text, x, y, color, shadow);
    } catch (Throwable t) {
      Simplehud.LOGGER.error("[SimpleHUD] drawString compatibility issue:");
      t.printStackTrace();
    }
  }
}
