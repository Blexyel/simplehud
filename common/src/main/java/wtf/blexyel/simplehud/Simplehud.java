package wtf.blexyel.simplehud;

import dev.architectury.platform.Platform;
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
    String version = Platform.getMinecraftVersion();
    if (!(version.compareTo("1.21.8") <= 0)) Keybindings.init();
  }
}
