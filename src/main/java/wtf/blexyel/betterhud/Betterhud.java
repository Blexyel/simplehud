package wtf.blexyel.betterhud;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Betterhud implements ModInitializer {
	public static final String MOD_ID = "better-hud";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        Config.HANDLER.load();

		LOGGER.info("Better HUD is alive!!");
	}
}