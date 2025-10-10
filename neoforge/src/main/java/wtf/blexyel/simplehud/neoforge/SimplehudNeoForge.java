package wtf.blexyel.simplehud.neoforge;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import wtf.blexyel.simplehud.Simplehud;
import net.neoforged.fml.common.Mod;
import wtf.blexyel.simplehud.YACLConfig;

@Mod(Simplehud.MOD_ID)
public final class SimplehudNeoForge {
    public SimplehudNeoForge() {
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> YACLConfig.create(parent)
        );
        // Run our common setup.
        Simplehud.init();
    }
}
