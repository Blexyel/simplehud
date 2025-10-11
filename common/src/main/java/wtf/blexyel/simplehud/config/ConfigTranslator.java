package wtf.blexyel.simplehud.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import java.lang.reflect.Field;

// Yes, this code is written by AI. I fucking hate java so fucking much! fuck this fucking fuck-ass
// language

// RUST FTW
public class ConfigTranslator {

  /**
   * Returns the static Config field that corresponds to the given enum option. Automatically
   * matches enum name to @SerialEntry field name.
   */
  public static Field getField(ConfigEnums option) {
    Field field;
    try {
      // Convert enum name to match field naming (adjust if your enum uses different style)
      String fieldName = option.name().toLowerCase(); // e.g., ENABLED -> enabled
      field = Config.class.getField(fieldName);

      // Ensure it’s annotated with @SerialEntry
      if (!field.isAnnotationPresent(SerialEntry.class)) {
        throw new RuntimeException("Field " + fieldName + " is not a @SerialEntry");
      }

      field.setAccessible(true);
      return field;
    } catch (NoSuchFieldException e) {
      throw new RuntimeException("No matching @SerialEntry field for " + option, e);
    }
  }
}
