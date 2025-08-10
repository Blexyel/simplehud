package wtf.blexyel.simplehud;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class SimpleHudStuff {
  public void getEntry(DrawContext context, MinecraftClient client) {
    boolean rfps = Config.fps;
    boolean rcoords = Config.coords;
    boolean rchunk = Config.chunk;
    boolean rping = Config.ping;
    boolean rconn = Config.conn;
    boolean rbiome = Config.biome;

    int fps = client.getCurrentFps();
    String fpsColor = (Config.fpscolor) ? (fps <= 30 ? "§c" : fps < 60 ? "§e" : "§a") : "";
    String fpsString = "FPS: " + fpsColor + fps;
    String coordsx = String.valueOf((int) client.player.getBlockPos().getX());
    String coordsy = String.valueOf((int) client.player.getBlockPos().getY());
    String coordsz = String.valueOf((int) client.player.getBlockPos().getZ());
    String coords =
        String.format(
            Config.coordscolor ? "XYZ: §c%s §a%s §9%s" : "XYZ: %s %s %s",
            coordsx,
            coordsy,
            coordsz);
    String chunkcoordsx = String.valueOf((int) client.player.getChunkPos().x);
    String chunkcoordsz = String.valueOf((int) client.player.getChunkPos().z);
    String chunkcoords =
        String.format(
            Config.chunkcolor ? "Chunk: §c%s §9%s" : "Chunk: %s %s", chunkcoordsx, chunkcoordsz);
    int ping = SimpleHudUtils.getPing(client);
    String pingColor =
        (Config.pingcolor) ? (ping > 300 ? "§4" : ping > 150 ? "§c" : ping > 80 ? "§e" : "§a") : "";
    String pingString = "Ping: " + pingColor + ping;
    String conn = SimpleHudUtils.getConnectionInfo(client);
    String biome = SimpleHudUtils.getBiome(client);

    int baseY = 5;
    int spacing = 10;

    if (!rfps && !rcoords && !rping && !rconn && !rbiome) return;

    // Gather all enabled indices
    List<Integer> indices = new ArrayList<>();
    if (rfps) indices.add(Config.fpsindex);
    if (rcoords) indices.add(Config.coordsindex);
    if (rchunk) indices.add(Config.chunkindex);
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
      render(context, client, fpsString, y, 0xFFFFFFFF);
    }
    if (rcoords) {
      int y = baseY + spacing * Config.coordsindex;
      render(context, client, coords, y, 0xFFFFFFFF);
    }
    if (rcoords) {
      int y = baseY + spacing * Config.coordsindex;
      render(context, client, coords, y, 0xFFFFFFFF);
    }
    if (rchunk) {
      int y = baseY + spacing * Config.chunkindex;
      render(context, client, chunkcoords, y, 0xFFFFFFFF);
    }
    if (rping) {
      int y = baseY + spacing * Config.pingindex;
      render(context, client, pingString, y, 0xFFFFFFFF);
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
    int sliderValue = Config.horizontalpos;

    sliderValue = MathHelper.clamp(sliderValue, 0, 100);

    int scaledWidth = client.getWindow().getScaledWidth();
    int textWidth = client.textRenderer.getWidth(text);

    int leftBound = 5;
    int rightBound = scaledWidth - textWidth - 5;

    int pos = leftBound + (rightBound - leftBound) * sliderValue / 100;

    context.drawText(client.textRenderer, text, pos, y, color, true);
  }
}
