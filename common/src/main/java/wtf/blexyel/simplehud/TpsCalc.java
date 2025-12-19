package wtf.blexyel.simplehud;

public class TpsCalc {
  private static long lastServerWorldTime = -1;
  private static long lastAnchorMs = -1;

  private static double estimatedTps = 20.0;

  private static final double SMOOTHING = 0.1;

  public static void onAnchor(long serverWorldTime) {
    long now = System.currentTimeMillis();

    if (lastAnchorMs != -1) {
      long deltaMs = now - lastAnchorMs;
      if (deltaMs < 5000) {
        double tps = (serverWorldTime - lastServerWorldTime) / (deltaMs / 1000.0);
        tps = Math.max(0.0, Math.min(20.0, tps));
        estimatedTps += (tps - estimatedTps) * SMOOTHING;
      } else {
        estimatedTps = 20.0;
      }
    }

    lastServerWorldTime = serverWorldTime;
    lastAnchorMs = now;
  }

  public static void onClientTick() {}

  public static double getTps() {
    return estimatedTps;
  }

  public static void onTimePacket(
      net.minecraft.network.protocol.game.ClientboundSetTimePacket packet) {
    onAnchor(packet.gameTime());
  }

  public static void reset() {
    lastServerWorldTime = -1;
    lastAnchorMs = -1;
    estimatedTps = 20.0;
  }

  private TpsCalc() {}
}
