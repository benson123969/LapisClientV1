package com.lapisclient.mixin;

import com.lapisclient.LapisClient;
import com.lapisclient.module.Module;
import com.lapisclient.module.modules.render.ZoomModule;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

	@Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
	private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
		Module zoomModule = LapisClient.getInstance().getModuleManager().getModuleByName("Zoom");
		if (zoomModule != null && zoomModule.isEnabled() && ZoomModule.zoomKeybind.isPressed()) {
			ZoomModule.setZooming(true);
			double fov = cir.getReturnValue();
			cir.setReturnValue(fov * ZoomModule.getZoomLevel());
		} else {
			ZoomModule.setZooming(false);
		}
	}
}
