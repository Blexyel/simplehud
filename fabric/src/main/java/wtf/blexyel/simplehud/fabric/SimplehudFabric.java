package wtf.blexyel.simplehud.fabric;

import net.fabricmc.api.ModInitializer;
import wtf.blexyel.simplehud.Simplehud;

public final class SimplehudFabric implements ModInitializer {

  @Override
  public void onInitialize() {

    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    // Run our common setup.
    Simplehud.init();
  }
}
