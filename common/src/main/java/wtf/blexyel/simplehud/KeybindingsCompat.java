package wtf.blexyel.simplehud;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import java.lang.reflect.Constructor;
import net.minecraft.client.KeyMapping;

public class KeybindingsCompat {

  public static KeyMapping ENABLED_KEY;
  public static String CATEGORY = "key.category.minecraft.simplehud.main";

  static {
    try {
      // Use reflection to call KeyMapping constructor with String
      Constructor<KeyMapping> constructor =
          KeyMapping.class.getConstructor(
              String.class, InputConstants.Type.class, int.class, String.class);
      ENABLED_KEY =
          constructor.newInstance("key.simplehud.enable", InputConstants.Type.KEYSYM, -1, CATEGORY);
    } catch (Exception e) {
      // throw new RuntimeException("Failed to initialize keybinding", e);
    }
  }

  public static void init() {
    try {
      KeyMappingRegistry.register(ENABLED_KEY);
    } catch (Exception e) {
      // throw new RuntimeException("Failed to register keybinding", e);
    }
  }
}
