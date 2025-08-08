package wtf.blexyel.simplehud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.math.BlockPos;

public class SimpleHudUtils {

    private static long lastPing = System.currentTimeMillis();
    private static int PING = 0;

    public static int getPing(MinecraftClient client) {

        if (System.currentTimeMillis() - lastPing > 2000) {
            lastPing = System.currentTimeMillis();
            PING = ping(client);
        }
        return PING;
    }

    public static String getConnectionInfo(MinecraftClient client) {
        if (client != null && client.player != null) {
            String conn = client.getCurrentServerEntry() != null ? client.getCurrentServerEntry().address : "idk???";
            if (client.isInSingleplayer())
                return "Singleplayer";
            return conn;
        }
        return "Unknown";
    }

    public static String getBiome(MinecraftClient client) {
        if (client.world != null) {
            assert client.getCameraEntity() != null;
            final BlockPos blockPos = client.getCameraEntity().getBlockPos();
            return String.format(client.world.getBiome(blockPos).getKey().get().getValue().toString());
        }
        return "Unknown";
    }

    private static int ping(MinecraftClient client) {
        int ping = 0;
        if (client != null && client.player != null) {
            PlayerListEntry e = client.player.networkHandler.getPlayerListEntry(client.player.getUuid());
            if (e != null) {
                ping = e.getLatency();
            }
        }
        return ping;
    }

}