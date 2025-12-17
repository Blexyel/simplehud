package wtf.blexyel.simplehud;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;

// import net.minecraft.client.KeyMapping.Category;

public class Keybindings {
  /*
  public static String CATEGORY =
      KeyMapping.Category.register(ResourceLocation.parse("simplehud.main"));
  */

  public static String CATEGORY = "simplehud.main";

  public static final KeyMapping ENABLED_KEY =
      new KeyMapping("key.simplehud.enable", InputConstants.Type.KEYSYM, -1, CATEGORY);

  public static void init() {
    KeyMappingRegistry.register(ENABLED_KEY);
  }
}
