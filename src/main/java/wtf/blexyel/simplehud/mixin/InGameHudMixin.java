package wtf.blexyel.simplehud.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import wtf.blexyel.simplehud.SimpleHudStuff;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "render", at = @At("TAIL"))
	public void render(DrawContext drawContext, RenderTickCounter tickCounter, CallbackInfo ci) {
		if (MinecraftClient.getInstance().options.hudHidden)
			return;
		SimpleHudStuff simpleHudStuff = new SimpleHudStuff();
		simpleHudStuff.getEntry(drawContext, client);
	}
}