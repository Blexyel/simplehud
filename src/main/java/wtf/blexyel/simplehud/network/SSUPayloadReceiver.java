package wtf.blexyel.simplehud.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SSUPayloadReceiver {
  public static boolean supported = false;

  public static boolean supported() {
    ClientPlayNetworking.registerGlobalReceiver(
        SSUPayload.TYPE,
        (payload, context) -> {
          supported = payload.supported();
        });
    return supported;
  }
}
