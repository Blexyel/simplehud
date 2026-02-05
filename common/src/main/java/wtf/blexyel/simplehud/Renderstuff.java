package wtf.blexyel.simplehud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;
import wtf.blexyel.simplehud.config.Config;

public class Renderstuff {
  public void getEntry(GuiGraphics context, Minecraft client) {
    boolean rfps = Config.fps;
    boolean rcoords = Config.coords;
    boolean rchunk = Config.chunk;
    boolean rping = Config.ping;
    boolean rconn = Config.conn;
    boolean rbiome = Config.biome;
    boolean rfacing = Config.facing;
    boolean rtps = Config.tps;

    int fps = client.getFps();
    String fpsColor = (Config.fpscolor) ? (fps <= 30 ? "§c" : fps < 60 ? "§e" : "§a") : "";
    String fpsString = "FPS: " + fpsColor + fps;
    String coordsx = String.valueOf((int) client.player.getX());
    String coordsy = String.valueOf((int) client.player.getY());
    String coordsz = String.valueOf((int) client.player.getZ());
    String coords =
        String.format(
            Config.coordscolor ? "XYZ: §c%s §a%s §9%s" : "XYZ: %s %s %s",
            coordsx,
            coordsy,
            coordsz);
    String chunkcoordsx = String.valueOf((int) client.player.chunkPosition().x);
    String chunkcoordsz = String.valueOf((int) client.player.chunkPosition().z);
    String chunkcoords =
        String.format(
            Config.chunkcolor ? "Chunk: §c%s §9%s" : "Chunk: %s %s", chunkcoordsx, chunkcoordsz);
    int ping = Utils.getPing(client);
    String pingColor =
        (Config.pingcolor) ? (ping > 300 ? "§4" : ping > 150 ? "§c" : ping > 80 ? "§e" : "§a") : "";
    String pingString = "Ping: " + pingColor + ping;
    String conn = Utils.getConnectionInfo(client);
    String biome = Utils.getBiome(client);
    String facing = Utils.getFacingDirection(client);
    String tpsColor =
        (Config.tpscolor)
            ? (Utils.getTPS(client) <= 15 ? "§c" : Utils.getTPS(client) < 18 ? "§e" : "§a")
            : "";
    String tpsString = "TPS: " + tpsColor + Utils.getTPS(client);

    int baseY = 5;
    int spacing = 10;

    List<Integer> indices = new ArrayList<>();
    if (rfps) indices.add(Config.fpsindex);
    if (rcoords) indices.add(Config.coordsindex);
    if (rchunk) indices.add(Config.chunkindex);
    if (rping) indices.add(Config.pingindex);
    if (rconn) indices.add(Config.connindex);
    if (rbiome) indices.add(Config.biomeindex);
    if (rfacing) indices.add(Config.facingindex);
    if (rtps) indices.add(Config.tpsindex);

    if (indices.isEmpty()) return;

    Set<Integer> unique = new HashSet<>(indices);
    if (unique.size() < indices.size()) {
      render(context, client, "ERROR: ONE OR MULTIPLE AT SAME POSITION", baseY, 0xFFFF0000);
      return;
    }

    List<Integer> sorted = new ArrayList<>(unique);
    Collections.sort(sorted);
    Map<Integer, Integer> adjusted = new HashMap<>();
    for (int i = 0; i < sorted.size(); i++) {
      adjusted.put(sorted.get(i), i);
    }

    if (rfps) {
      int adj = adjusted.get(Config.fpsindex);
      int y = baseY + spacing * adj;
      render(context, client, fpsString, y, 0xFFFFFFFF);
    }
    if (rcoords) {
      int adj = adjusted.get(Config.coordsindex);
      int y = baseY + spacing * adj;
      render(context, client, coords, y, 0xFFFFFFFF);
    }
    if (rchunk) {
      int adj = adjusted.get(Config.chunkindex);
      int y = baseY + spacing * adj;
      render(context, client, chunkcoords, y, 0xFFFFFFFF);
    }
    if (rping) {
      int adj = adjusted.get(Config.pingindex);
      int y = baseY + spacing * adj;
      render(context, client, pingString, y, 0xFFFFFFFF);
    }
    if (rtps) {
      int adj = adjusted.get(Config.tpsindex);
      int y = baseY + spacing * adj;
      render(context, client, tpsString, y, 0xFFFFFFFF);
    }
    if (rconn) {
      int adj = adjusted.get(Config.connindex);
      int y = baseY + spacing * adj;
      render(context, client, "IP: " + conn, y, 0xFFFFFFFF);
    }
    if (rbiome) {
      int adj = adjusted.get(Config.biomeindex);
      int y = baseY + spacing * adj;
      render(context, client, "Biome: " + biome, y, 0xFFFFFFFF);
    }
    if (rfacing) {
      int adj = adjusted.get(Config.facingindex);
      int y = baseY + spacing * adj;
      render(context, client, "Facing: " + facing, y, 0xFFFFFFFF);
    }
  }

  public void render(GuiGraphics context, Minecraft client, String text, int y, int color) {
    int sliderValue = Config.horizontalpos;

    sliderValue = Mth.clamp(sliderValue, 0, 100);

    int scaledWidth = client.getWindow().getGuiScaledWidth();
    int textWidth = client.font.width(text);

    int leftBound = 5;
    int rightBound = scaledWidth - textWidth - 5;

    int pos = leftBound + (rightBound - leftBound) * sliderValue / 100;

    int entryHeight = client.font.lineHeight;

    // context.drawString(client.font, text, pos, y, color, true);

    if (Config.background) {
      context.fill(pos - 1, y - 1, pos + textWidth + 1, y + entryHeight, 0x55000000);
    }
    RenderHelper.drawString(context, client.font, text, pos, y, color, true);
  }
}
