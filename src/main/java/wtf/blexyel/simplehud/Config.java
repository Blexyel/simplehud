package wtf.blexyel.simplehud;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Config {
  public static final ConfigClassHandler<Config> HANDLER =
      ConfigClassHandler.createBuilder(Config.class)
          .id(Identifier.tryParse("simplehud:config"))
          .serializer(
              config ->
                  GsonConfigSerializerBuilder.create(config)
                      .setPath(FabricLoader.getInstance().getConfigDir().resolve("simplehud.json5"))
                      .appendGsonBuilder(builder -> builder.setPrettyPrinting())
                      .setJson5(true)
                      .build())
          .build();

  // ### START TOGGLES ### //

  @SerialEntry public static boolean fps = true;

  @SerialEntry public static boolean coords = true;

  @SerialEntry public static boolean ping = true;

  @SerialEntry public static boolean conn = true;

  @SerialEntry public static boolean biome = true;

  @SerialEntry public static boolean chunk = false;

  // ### END TOGGLES ### //

  // ### START INDICES ### //

  @SerialEntry public static int fpsindex = 0;

  @SerialEntry public static int coordsindex = 1;

  @SerialEntry public static int chunkindex = 2;

  @SerialEntry public static int pingindex = 3;

  @SerialEntry public static int connindex = 4;

  @SerialEntry public static int biomeindex = 5;

  // ### END INDICES ### //

  // ### START SLIDERVAL ### //

  @SerialEntry public static int horizontalpos = 0;

  // ### END SLIDERVAL ### //
}
