package wtf.blexyel.simplehud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Simplehud {
    public static final String MOD_ID = "simplehud";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        // Write common init code here.
        LOGGER.info("Simple HUD is alive!!");

        Config.HANDLER.load();
    }
}