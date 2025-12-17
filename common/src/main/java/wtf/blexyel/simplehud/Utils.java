package wtf.blexyel.simplehud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.client.gui.components.DebugScreenOverlay;

public class Utils {

  private static long lastPing = System.currentTimeMillis();
  private static int PING = 0;

  public static int getPing(Minecraft client) {
    if (System.currentTimeMillis() - lastPing > 2000) {
      lastPing = System.currentTimeMillis();
      PING = ping(client);
    }
    return PING;
  }

  public static String getConnectionInfo(Minecraft client) {
    if (client != null && client.player != null) {
      String conn = client.getCurrentServer() != null ? client.getCurrentServer().ip : "idk???";
      if (client.isLocalServer()) return "Singleplayer";
      return conn;
    }
    return "Unknown";
  }

  public static String getBiome(Minecraft client) {
    if (client.level != null) {
      assert client.getCameraEntity() != null;
      final BlockPos blockPos = client.getCameraEntity().getOnPos();
      return (String)client.level.getBiome(blockPos).unwrap().map((resourceKey) -> {
         return resourceKey.location().toString();
      }, (biome) -> {
         return "[unregistered " + biome + "]";
      });
    }
    return "Unknown";
  }

  // do this at some point (possibly with server integration (aka. packets))
  public static float getTPS(Minecraft client) {
    if (client != null && client.level != null) {
      float time = System.currentTimeMillis();

      return Mth.clamp(0.0F /*client.gui.getGuiTicks()*/, 0.0F, 1000.0F);
    }
    return 0.0F;
  }

  public static String getFacingDirection(Minecraft client) {
    if (client != null && client.player != null) {
      return client.player.getDirection().toString();
    }
    return "Unknown";
  }

  private static int ping(Minecraft client) {
    int ping = 0;
    if (client != null && client.player != null) {
      PlayerInfo e = client.player.connection.getPlayerInfo(client.player.getUUID());
      if (e != null) {
        ping = e.getLatency();
      }
    }
    return ping;
  }
}
