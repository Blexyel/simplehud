package wtf.blexyel.simplehud.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class YACLConfig {
  public static Screen create(Screen parent) {
    Config.HANDLER.load();
    return YetAnotherConfigLib.createBuilder()
        .title(Component.literal("Simple HUD Config"))
        // ### START TOGGLES ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Component.literal("General"))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "Enabled", "Enables/Disables the HUD", ConfigEnums.ENABLED))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "FPS", "Shows the current FPS", ConfigEnums.FPS))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "Coordinates", "Shows the current coordinates", ConfigEnums.COORDS))
                .option(
                    YACLConfigHelper.booleanOption(
                        true,
                        "Chunk Coordinates",
                        "Shows the current chunk coordinates",
                        ConfigEnums.CHUNK))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "Ping", "Shows the current ping", ConfigEnums.PING))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "Connection/IP", "Shows the current connection/IP", ConfigEnums.CONN))
                .option(
                    YACLConfigHelper.booleanOption(
                        true, "Biome", "Shows the current biome", ConfigEnums.BIOME))
                .option(
                    YACLConfigHelper.booleanOption(
                        false, "Facing", "Shows the direction you are facing", ConfigEnums.FACING))
                .build())
        // ### END TOGGLES ### //
        // ### START POSITIONS ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Component.literal("Positions"))
                .option(
                    YACLConfigHelper.intOption(
                        0,
                        "FPS Position Index",
                        "Sets the position index for the FPS display",
                        ConfigEnums.FPSINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        1,
                        "Coordinates Position Index",
                        "Sets the position index for the Coordinates display",
                        ConfigEnums.COORDSINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        2,
                        "Chunk Coordinates Position Index",
                        "Sets the position index for the Chunk Coordinates display",
                        ConfigEnums.CHUNKINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        3,
                        "Ping Position Index",
                        "Sets the position index for the Ping display",
                        ConfigEnums.PINGINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        4,
                        "Connection/IP Position Index",
                        "Sets the position index for the Connection/IP display",
                        ConfigEnums.CONNINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        5,
                        "Biome Position Index",
                        "Sets the position index for the Biome display",
                        ConfigEnums.BIOMEINDEX,
                        indices))
                .option(
                    YACLConfigHelper.intOption(
                        6,
                        "Facing Position Index",
                        "Sets the position index for the Facing display (North, South, etc.)",
                        ConfigEnums.FACINGINDEX,
                        indices))
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Horizontal Position"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Sets the horizontal position of the HUD elements")))
                        .binding(0, () -> Config.horizontalpos, v -> Config.horizontalpos = v)
                        .controller(
                            opt -> IntegerSliderControllerBuilder.create(opt).range(0, 100).step(1))
                        .build())
                .build())
        // ### END POSITIONS ### //
        // ### START MISC ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Component.literal("Misc"))
                .option(
                    YACLConfigHelper.booleanOption(
                        false, "FPS colors", "Makes FPS gay", ConfigEnums.FPSCOLOR))
                .option(
                    YACLConfigHelper.booleanOption(
                        false,
                        "Coordinates colors",
                        "Makes Coordinates gay",
                        ConfigEnums.COORDSCOLOR))
                .option(
                    YACLConfigHelper.booleanOption(
                        false, "Ping color", "Makes Ping gay", ConfigEnums.PINGCOLOR))
                .option(
                    YACLConfigHelper.booleanOption(
                        false,
                        "Chunk color",
                        "Makes Chunk coordinates gay",
                        ConfigEnums.CHUNKCOLOR))
                .option(
                    YACLConfigHelper.booleanOption(
                        false, "Background", "Enable text background", ConfigEnums.BACKGROUND))
                .build())
        // ### END MISC ### //
        .save(Config.HANDLER::save)
        .build()
        .generateScreen(parent);
  }

  public static final List<Integer> indices =
      IntStream.range(0, getMaxIndex()).boxed().collect(Collectors.toList());

  public static int getMaxIndex() {
    int count = 0;
    for (Field field : Config.class.getDeclaredFields()) {
      if (field.isAnnotationPresent(SerialEntry.class)) {
        if (field.getName().toLowerCase().contains("index")) {
          count++;
        }
      }
    }
    return count;
  }
}
