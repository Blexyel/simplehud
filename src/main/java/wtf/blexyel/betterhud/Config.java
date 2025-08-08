package wtf.blexyel.betterhud;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Config {
    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(Identifier.tryParse("betterhud:config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("betterhud.json5"))
                    .appendGsonBuilder(builder -> builder.setPrettyPrinting())
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public static boolean fps = true;

    @SerialEntry
    public static boolean coords = true;

    @SerialEntry
    public static boolean ping = true;

    @SerialEntry
    public static boolean conn = true;

    @SerialEntry
    public static boolean biome = true;
}