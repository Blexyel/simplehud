package wtf.blexyel.simplehud.config;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.gui.controllers.cycling.CyclingListController;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.network.chat.Component;

public class YACLConfigHelper {
  public static Option<Boolean> booleanOption(
      boolean defaultValue, String name, String description, ConfigEnums option) {

    Field configField = ConfigTranslator.getField(option);

    return Option.<Boolean>createBuilder()
        .name(Component.literal(name))
        .description(OptionDescription.of(Component.literal(description)))
        .binding(
            defaultValue,
            () -> {
              try {
                return configField.getBoolean(null);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            },
            newVal -> {
              try {
                configField.setBoolean(null, newVal);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            })
        .controller(TickBoxControllerBuilder::create)
        .build();
  }

  public static Option<Integer> intOption(
      int defaultValue,
      String name,
      String description,
      ConfigEnums configoption,
      List<Integer> indices) {

    Field configField = ConfigTranslator.getField(configoption);

    return Option.<Integer>createBuilder()
        .name(Component.literal(name))
        .description(OptionDescription.of(Component.literal(description)))
        .binding(
            defaultValue,
            () -> {
              try {
                return configField.getInt(null);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            },
            newVal -> {
              try {
                configField.setInt(null, newVal);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            })
        .controller(option -> () -> new CyclingListController<>(option, indices))
        .build();
  }
}
