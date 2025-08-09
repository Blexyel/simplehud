package wtf.blexyel.simplehud;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class SimpleHudStuff {
  public void getEntry(DrawContext context, MinecraftClient client) {
    boolean rfps = Config.fps;
    boolean rcoords = Config.coords;
    boolean rping = Config.ping;
    boolean rconn = Config.conn;
    boolean rbiome = Config.biome;

    int fps = client.getCurrentFps();
    String coords =
        "XYZ: "
            + (int) client.player.getX()
            + " "
            + (int) client.player.getY()
            + " "
            + (int) client.player.getZ();
    int ping = SimpleHudUtils.getPing(client);
    String conn = SimpleHudUtils.getConnectionInfo(client);
    String biome = SimpleHudUtils.getBiome(client);

    int baseY = 5;
    int spacing = 10;

    if (!rfps && !rcoords && !rping && !rconn && !rbiome) return;

    // Gather all enabled indices
    List<Integer> indices = new ArrayList<>();
    if (rfps) indices.add(Config.fpsindex);
    if (rcoords) indices.add(Config.coordsindex);
    if (rping) indices.add(Config.pingindex);
    if (rconn) indices.add(Config.connindex);
    if (rbiome) indices.add(Config.biomeindex);

    // Check for duplicates
    Set<Integer> unique = new HashSet<>(indices);
    if (unique.size() < indices.size()) {
      // Collision found
      render(context, client, "ERROR: ONE OR MULTIPLE AT SAME POSITION", baseY, 0xFFFF0000);
      return;
    }

    // No collisions, render normally
    if (rfps) {
      int y = baseY + spacing * Config.fpsindex;
      render(context, client, "FPS: " + fps, y, 0xFFFFFFFF);
    }
    if (rcoords) {
      int y = baseY + spacing * Config.coordsindex;
      render(context, client, coords, y, 0xFFFFFFFF);
    }
    if (rping) {
      int y = baseY + spacing * Config.pingindex;
      render(context, client, "Ping: " + ping, y, 0xFFFFFFFF);
    }
    if (rconn) {
      int y = baseY + spacing * Config.connindex;
      render(context, client, "IP: " + conn, y, 0xFFFFFFFF);
    }
    if (rbiome) {
      int y = baseY + spacing * Config.biomeindex;
      render(context, client, "Biome: " + biome, y, 0xFFFFFFFF);
    }
  }

  public void render(DrawContext context, MinecraftClient client, String text, int y, int color) {
    context.drawText(client.textRenderer, text, 5, y, color, true);
  }
}
