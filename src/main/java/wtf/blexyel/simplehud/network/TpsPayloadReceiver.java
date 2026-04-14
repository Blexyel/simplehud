package wtf.blexyel.simplehud.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class TpsPayloadReceiver {
  public static double tps = 0;

  public static double tps() {
    ClientPlayNetworking.registerGlobalReceiver(
        TpsPayload.TYPE,
        (payload, context) -> {
          tps = payload.tps();
        });
    return tps;
  }
}
