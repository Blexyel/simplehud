package wtf.blexyel.simplehud;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class GuiHelper {
    @ExpectPlatform
    public static void drawString(GuiGraphics context, Font font, String text, int x, int y, int color, boolean shadow) {
        throw new AssertionError("Platform implementation missing");
    }
}
