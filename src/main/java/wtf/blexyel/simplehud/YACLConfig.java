package wtf.blexyel.simplehud;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.gui.controllers.cycling.CyclingListController;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class YACLConfig {
  public static Screen create(Screen parent) {
    Config.HANDLER.load();
    return YetAnotherConfigLib.createBuilder()
        .title(Text.literal("Simple HUD Config"))
        // ### START TOGGLES ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Text.literal("General"))
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Text.literal("FPS"))
                        .description(OptionDescription.of(Text.literal("Shows the current FPS")))
                        .binding(Config.fps, () -> Config.fps, newVal -> Config.fps = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Text.literal("Coordinates"))
                        .description(
                            OptionDescription.of(Text.literal("Shows the current coordinates")))
                        .binding(
                            Config.coords, () -> Config.coords, newVal -> Config.coords = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Text.literal("Ping"))
                        .description(OptionDescription.of(Text.literal("Shows the current ping")))
                        .binding(Config.ping, () -> Config.ping, newVal -> Config.ping = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Text.literal("Connection/IP"))
                        .description(
                            OptionDescription.of(Text.literal("Shows the current connection/IP")))
                        .binding(Config.conn, () -> Config.conn, newVal -> Config.conn = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .option(
                    Option.<Boolean>createBuilder()
                        .name(Text.literal("Biome"))
                        .description(OptionDescription.of(Text.literal("Shows the current biome")))
                        .binding(Config.biome, () -> Config.biome, newVal -> Config.biome = newVal)
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                .build())
        // ### END TOGGLES ### //
        // ### START POSITIONS ### //
        .category(
            ConfigCategory.createBuilder()
                .name(Text.literal("Positions: 0 is top, x is bottom."))
                .option(
                    Option.<Integer>createBuilder()
                        .name(Text.literal("FPS Position Index"))
                        .description(
                            OptionDescription.of(
                                Text.literal("Sets the position index for the FPS display")))
                        .binding(0, () -> Config.fpsindex, v -> Config.fpsindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Text.literal("Coordinates Position Index"))
                        .description(
                            OptionDescription.of(
                                Text.literal(
                                    "Sets the position index for the coordinates display")))
                        .binding(1, () -> Config.coordsindex, v -> Config.coordsindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Text.literal("Ping Position Index"))
                        .description(
                            OptionDescription.of(
                                Text.literal("Sets the position index for the Ping display")))
                        .binding(2, () -> Config.pingindex, v -> Config.pingindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Text.literal("Connection/IP Position Index"))
                        .description(
                            OptionDescription.of(
                                Text.literal(
                                    "Sets the position index for the Connection/IP display")))
                        .binding(3, () -> Config.connindex, v -> Config.connindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .option(
                    Option.<Integer>createBuilder()
                        .name(Text.literal("Biome Position Index"))
                        .description(
                            OptionDescription.of(
                                Text.literal("Sets the position index for the Biome display")))
                        .binding(4, () -> Config.biomeindex, v -> Config.biomeindex = v)
                        .controller(option -> () -> new CyclingListController<>(option, indices))
                        .build())
                .build())
        // ### END POSITIONS ### //
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
