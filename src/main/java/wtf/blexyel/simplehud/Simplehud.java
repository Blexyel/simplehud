package wtf.blexyel.simplehud;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.blexyel.simplehud.config.Config;
import wtf.blexyel.simplehud.config.YACLConfig;

public final class Simplehud implements ModInitializer {
  public static final String MOD_ID = "simplehud";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    // Write common init code here.
    LOGGER.info("Simple HUD is alive!!");

    Config.HANDLER.load();
    Keybindings.init();

    ClientLifecycleEvents.CLIENT_STARTED.register(
        (minecraft) -> {
          TpsCalc.reset();
        });

    // Register client stop / disconnect hook
    ClientLifecycleEvents.CLIENT_STOPPING.register(
        minecraft -> {
          TpsCalc.reset();
        });

    ClientTickEvents.END_CLIENT_TICK.register(
        mc -> {
          while (wtf.blexyel.simplehud.Keybindings.ENABLED_KEY.consumeClick()) {
            Config.enabled = !Config.enabled;
            Config.HANDLER.save();
          }
          while (Keybindings.CONFIG_KEY.consumeClick()) {
            mc.setScreenAndShow(YACLConfig.create(mc.gui.screen()));
          }
        });
  }
}
