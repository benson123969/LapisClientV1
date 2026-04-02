package com.lapisclient.mixin;

import com.lapisclient.LapisClient;
import com.lapisclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	@Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"))
	private void onDisconnect(CallbackInfo ci) {
		// Save configuration when disconnecting
		if (LapisClient.getInstance() != null) {
			LapisClient.getInstance().getConfigManager().save();
		}
	}

	@Inject(method = "stop", at = @At("HEAD"))
	private void onStop(CallbackInfo ci) {
		// Save configuration and stop notifier when closing game
		if (LapisClient.getInstance() != null) {
			LapisClient.getInstance().getConfigManager().save();
			LapisClient.getInstance().getYouTubeNotifier().stop();
		}
	}
}
