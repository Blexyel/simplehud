package wtf.blexyel.simplehud;

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
        String coords = "XYZ: " + (int) client.player.getX() + " " + (int) client.player.getY() + " "
                + (int) client.player.getZ();
        int ping = SimpleHudUtils.getPing(client);
        String conn = SimpleHudUtils.getConnectionInfo(client);
        String biome = SimpleHudUtils.getBiome(client);

        if (!rfps && !rcoords && !rping && !rconn && !rbiome)
            return;

        if (rfps)
            render(context, client, "FPS: " + fps, 5, 0xFFFFFFFF);
        if (rcoords)
            render(context, client, coords, 15, 0xFFFFFFFF);
        if (rping)
            render(context, client, "Ping: " + ping, 25, 0xFFFFFFFF);
        if (rconn)
            render(context, client, "IP: " + conn, 35, 0xFFFFFFFF);

        if (rbiome)
            render(context, client, "Biome: " + biome, 45, 0xFFFFFFFF);
    }

    public void render(DrawContext context, MinecraftClient client, String text, int y, int color) {
        context.drawText(client.textRenderer, text, 5, y, color, true);
    }
}