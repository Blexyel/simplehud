package wtf.blexyel.simplehud.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simplehud.Renderstuff;
import wtf.blexyel.simplehud.config.Config;

@Mixin(Gui.class)
public class GuiMixin {
  @Shadow @Final private Minecraft minecraft;
  @Shadow @Final private GuiRenderState guiRenderState;

  @Inject(method = "extractRenderState", at = @At("TAIL"))
  public void render(
      DeltaTracker deltaTracker,
      boolean shouldRenderLevel,
      boolean resourcesLoaded,
      CallbackInfo ci) {
    GuiGraphicsExtractor graphics = new GuiGraphicsExtractor(minecraft, guiRenderState, 100, 100);
    // If GUI hidden, F3 visible or enabled flag false, dont do shit
    if (Minecraft.getInstance().gui.hud.isHidden()
        || !Config.enabled
        || Minecraft.getInstance().gui.hud.getDebugOverlay().showDebugScreen()
        || (Minecraft.getInstance().gui.screen() != null
            && Minecraft.getInstance().gui.screen().isPauseScreen())) return;
    Renderstuff renderstuff = new Renderstuff();
    renderstuff.getEntry(graphics, minecraft);
  }
}
