package wtf.blexyel.simplehud;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import net.minecraft.resources.Identifier;

public class Keybindings {
  public static Category CATEGORY =
      KeyMapping.Category.register(Identifier.parse("simplehud.main"));

  public static final KeyMapping ENABLED_KEY =
      new KeyMapping("key.simplehud.enable", InputConstants.Type.KEYSYM, -1, CATEGORY);

  public static final KeyMapping CONFIG_KEY =
      new KeyMapping("key.simplehud.config", InputConstants.Type.KEYSYM, -1, CATEGORY);

  public static void init() {
    KeyMappingHelper.registerKeyMapping(ENABLED_KEY);
    KeyMappingHelper.registerKeyMapping(CONFIG_KEY);
  }
}
