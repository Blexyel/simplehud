package wtf.blexyel.betterhud;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class YACLConfig {
    public static Screen create(Screen parent) {
        Config.HANDLER.load();
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Better HUD Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("General"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("FPS"))
                                .description(OptionDescription.of(Text.literal("Shows the current FPS")))
                                .binding(Config.fps, () -> Config.fps, newVal -> Config.fps = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Coordinates"))
                                .description(OptionDescription.of(Text.literal("Shows the current coordinates")))
                                .binding(Config.coords, () -> Config.coords, newVal -> Config.coords = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Ping"))
                                .description(OptionDescription.of(Text.literal("Shows the current ping")))
                                .binding(Config.ping, () -> Config.ping, newVal -> Config.ping = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Connection"))
                                .description(OptionDescription.of(Text.literal("Shows the current connection")))
                                .binding(Config.conn, () -> Config.conn, newVal -> Config.conn = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.literal("Biome"))
                                .description(OptionDescription.of(Text.literal("Shows the current biome")))
                                .binding(Config.biome, () -> Config.biome, newVal -> Config.biome = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build()
                )
                .save(Config.HANDLER::save)
                .build()
                .generateScreen(parent);
    }
}