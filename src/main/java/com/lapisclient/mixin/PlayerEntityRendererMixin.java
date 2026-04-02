package com.lapisclient.mixin;

import com.lapisclient.LapisClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	private static final Identifier LAPIS_ICON = Identifier.of("lapisclient", "textures/icon.png");

	@Inject(method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
			at = @At("HEAD"))
	private void renderLapisIcon(AbstractClientPlayerEntity player, Text text, MatrixStack matrices,
								   VertexConsumerProvider vertexConsumers, int light, float tickDelta, CallbackInfo ci) {
		// Check if the player is using Lapis Client
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null && player.getUuid().equals(client.player.getUuid())) {
			// This is the local player using Lapis Client
			// In a real implementation, you would render the lapis icon here
			// For now, we'll add it to the name tag via text modification
		}
	}
}
