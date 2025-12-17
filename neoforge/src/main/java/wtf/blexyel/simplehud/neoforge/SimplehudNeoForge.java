package wtf.blexyel.simplehud.neoforge;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import wtf.blexyel.simplehud.Simplehud;
import wtf.blexyel.simplehud.config.YACLConfig;

@Mod(Simplehud.MOD_ID)
public final class SimplehudNeoForge {
  public SimplehudNeoForge() {
    ModLoadingContext.get()
        .registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () ->
                new ConfigScreenHandler.ConfigScreenFactory(
                    (client, parent) -> YACLConfig.create(parent)));
    // Run our common setup.
    Simplehud.init();
  }
}
