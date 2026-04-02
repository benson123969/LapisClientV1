package com.lapisclient.mixin;

import com.lapisclient.LapisClient;
import com.lapisclient.module.Module;
import com.lapisclient.module.modules.hud.*;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Inject(method = "render", at = @At("TAIL"))
	private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
		// Render all enabled HUD modules
		for (Module module : LapisClient.getInstance().getModuleManager().getEnabledModules()) {
			if (module instanceof ArmorHudModule) {
				((ArmorHudModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			} else if (module instanceof FpsIndicatorModule) {
				((FpsIndicatorModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			} else if (module instanceof PingIndicatorModule) {
				((PingIndicatorModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			} else if (module instanceof PotionIndicatorModule) {
				((PotionIndicatorModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			} else if (module instanceof CoordinatesModule) {
				((CoordinatesModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			} else if (module instanceof DirectionModule) {
				((DirectionModule) module).render(context, context.getScaledWindowWidth(), context.getScaledWindowHeight());
			}
		}
	}

	@Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
	private void onRenderFireOverlay(DrawContext context, net.minecraft.util.Identifier texture, float opacity, CallbackInfo ci) {
		// Low fire module - reduce fire overlay
		Module lowFire = LapisClient.getInstance().getModuleManager().getModuleByName("Low Fire");
		if (lowFire != null && lowFire.isEnabled()) {
			if (texture.getPath().contains("fire")) {
				ci.cancel();
			}
		}
	}
}
