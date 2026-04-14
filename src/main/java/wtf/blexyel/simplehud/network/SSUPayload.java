package wtf.blexyel.simplehud.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SSUPayload(boolean supported) implements CustomPacketPayload {
  public static final Identifier TPS_PAYLOAD_ID =
      Identifier.fromNamespaceAndPath("ssu", "supported_payload");
  public static final Type<SSUPayload> TYPE = new Type<>(TPS_PAYLOAD_ID);
  public static final StreamCodec<RegistryFriendlyByteBuf, SSUPayload> CODEC =
      StreamCodec.composite(ByteBufCodecs.BOOL, SSUPayload::supported, SSUPayload::new);

  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
