package wtf.blexyel.simplehud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import wtf.blexyel.simplehud.network.SSUPayload;
import wtf.blexyel.simplehud.network.TpsPayload;

public class SimplehudClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    PayloadTypeRegistry.clientboundPlay().register(TpsPayload.TYPE, TpsPayload.CODEC);
    PayloadTypeRegistry.clientboundPlay().register(SSUPayload.TYPE, SSUPayload.CODEC);
  }
}
