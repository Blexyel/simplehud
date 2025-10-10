package wtf.blexyel.simplehud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.BlockPos;

public class SimpleHudUtils {

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
      final BlockPos blockPos = client.getCameraEntity().blockPosition();
      return String.format(client.level.getBiome(blockPos).unwrapKey().get().location().toString());
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
