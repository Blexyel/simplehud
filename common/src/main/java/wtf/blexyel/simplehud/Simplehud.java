package wtf.blexyel.simplehud;

import dev.architectury.event.events.client.ClientTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.blexyel.simplehud.config.Config;

public final class Simplehud {
  public static final String MOD_ID = "simplehud";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public static void init() {
    // Write common init code here.
    LOGGER.info("Simple HUD is alive!!");

    Config.HANDLER.load();
    boolean keybindingsLoaded = false;

    try {
      // Try new Keybindings
      Class<?> keybindingsClass = Class.forName("wtf.blexyel.simplehud.Keybindings");
      keybindingsClass.getMethod("init").invoke(null);

      // Object enabledKey = keybindingsClass.getField("ENABLED_KEY").get(null);
      // var consumeClickMethod = enabledKey.getClass().getMethod("consumeClick");

      ClientTickEvent.CLIENT_POST.register(
          mc -> {
            while (wtf.blexyel.simplehud.Keybindings.ENABLED_KEY.consumeClick()) {
              Config.enabled = !Config.enabled;
              Config.HANDLER.save();
            }
          });

      keybindingsLoaded = true; // success

    } catch (Throwable t) {
      // only log if we actually need the fallback
      Simplehud.LOGGER.info("New Keybindings failed to load, trying fallback...");
    }

    // only attempt fallback if first attempt failed
    if (!keybindingsLoaded) {
      try {
        Class<?> compatClass = Class.forName("wtf.blexyel.simplehud.KeybindingsCompat");
        compatClass.getMethod("init").invoke(null);

        // Object enabledKeyCompat = compatClass.getField("ENABLED_KEY").get(null);
        // var consumeClickCompat = enabledKeyCompat.getClass().getMethod("consumeClick");

        ClientTickEvent.CLIENT_POST.register(
            mc -> {
              while (wtf.blexyel.simplehud.KeybindingsCompat.ENABLED_KEY.consumeClick()) {
                Config.enabled = !Config.enabled;
                Config.HANDLER.save();
              }
            });

        Simplehud.LOGGER.info("Fallback KeybindingsCompat loaded successfully.");

      } catch (Throwable fallbackError) {
        Simplehud.LOGGER.error("No compatible keybindings class could be loaded!", fallbackError);
      }
    }
  }
}
