package wtf.blexyel.simplehud;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.cycling.CyclingListController;
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
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("FPS"))
                        .description(
                            OptionDescription.of(Component.literal("Shows the current FPS")))
                        .binding(true, () -> Config.fps, newVal -> Config.fps = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Coordinates"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Shows the current coordinates")))
                        .binding(true, () -> Config.coords, newVal -> Config.coords = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Chunk Coordinates"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Shows the current chunk coordinates")))
                        .binding(true, () -> Config.chunk, newVal -> Config.chunk = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Ping"))
                        .description(
                            OptionDescription.of(Component.literal("Shows the current ping")))
                        .binding(true, () -> Config.ping, newVal -> Config.ping = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Connection/IP"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Shows the current connection/IP")))
                        .binding(true, () -> Config.conn, newVal -> Config.conn = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Biome"))
                        .description(
                            OptionDescription.of(Component.literal("Shows the current biome")))
                        .binding(true, () -> Config.biome, newVal -> Config.biome = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .build())
        // ### END TOGGLES ### //
        // ### START POSITIONS ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Component.literal("Positions"))
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("FPS Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Sets the position index for the FPS display")))
                        .binding(0, () -> Config.fpsindex, v -> Config.fpsindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Coordinates Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Sets the position index for the coordinates display")))
                        .binding(1, () -> Config.coordsindex, v -> Config.coordsindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Chunk Coordinates Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Sets the position index for the Chunk coordinates display")))
                        .binding(2, () -> Config.chunkindex, v -> Config.chunkindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Ping Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Sets the position index for the Ping display")))
                        .binding(3, () -> Config.pingindex, v -> Config.pingindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Connection/IP Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal(
                                    "Sets the position index for the Connection/IP display")))
                        .binding(4, () -> Config.connindex, v -> Config.connindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Component.literal("Biome Position Index"))
                        .description(
                            OptionDescription.of(
                                Component.literal("Sets the position index for the Biome display")))
                        .binding(5, () -> Config.biomeindex, v -> Config.biomeindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
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
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("FPS colors"))
                        .description(OptionDescription.of(Component.literal("Makes FPS gay")))
                        .binding(false, () -> Config.fpscolor, v -> Config.fpscolor = v)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Coordinates colors"))
                        .description(
                            OptionDescription.of(Component.literal("Makes Coordinates gay")))
                        .binding(false, () -> Config.coordscolor, v -> Config.coordscolor = v)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Ping color"))
                        .description(OptionDescription.of(Component.literal("Makes Ping gay")))
                        .binding(false, () -> Config.pingcolor, v -> Config.pingcolor = v)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Component.literal("Chunk color"))
                        .description(
                            OptionDescription.of(Component.literal("Makes Chunk coordinates gay")))
                        .binding(false, () -> Config.chunkcolor, v -> Config.chunkcolor = v)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
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
