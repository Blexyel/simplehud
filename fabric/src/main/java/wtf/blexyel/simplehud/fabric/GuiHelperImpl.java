package wtf.blexyel.simplehud.fabric;

import java.lang.reflect.Method;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class GuiHelperImpl {
  private static Method drawStringMethod;

  public static void drawString(
      GuiGraphics context, Font font, String text, int x, int y, int color, boolean shadow) {
    try {
      if (drawStringMethod == null) {
        System.out.println("Doing reflection for multi-version compat");
        // this will break, no doubt
        drawStringMethod =
            GuiGraphics.class.getDeclaredMethod(
                "method_51433",
                Font.class,
                String.class,
                int.class,
                int.class,
                int.class,
                boolean.class);
        drawStringMethod.setAccessible(true);
      }

      // Invoke the method (works whether it returns void or int)
      drawStringMethod.invoke(context, font, text, x, y, color, shadow);

    } catch (Exception e) {
      System.err.println("[SimpleHUD/Fabric] drawString compatibility issue:");
      e.printStackTrace();
    }
  }
}
