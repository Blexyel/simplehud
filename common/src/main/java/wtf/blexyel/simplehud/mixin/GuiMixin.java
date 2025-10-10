package wtf.blexyel.simplehud.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.blexyel.simplehud.Renderstuff;

@Mixin(Gui.class)
public class GuiMixin {
  @Shadow @Final private Minecraft minecraft;

  @Inject(method = "render", at = @At("TAIL"))
  public void render(GuiGraphics drawContext, DeltaTracker tickCounter, CallbackInfo ci) {
    if (Minecraft.getInstance().options.hideGui) return;
    Renderstuff renderstuff = new Renderstuff();
    renderstuff.getEntry(drawContext, minecraft);
  }
}