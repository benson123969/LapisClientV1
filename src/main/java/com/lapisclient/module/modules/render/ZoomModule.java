package com.lapisclient.module.modules.render;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ZoomModule extends Module {
	public static KeyBinding zoomKeybind;
	private static boolean isZooming = false;
	private static final float ZOOM_LEVEL = 0.25f;

	public ZoomModule() {
		super("Zoom", "Zoom in with C key", ModuleCategory.RENDER);
		this.enabled = true;

		// Register zoom keybind
		zoomKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.lapisclient.zoom",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_C,
			"category.lapisclient"
		));
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {
		isZooming = false;
	}

	@Override
	public void onRender(float delta) {}

	public static boolean isZooming() {
		return isZooming;
	}

	public static void setZooming(boolean zooming) {
		isZooming = zooming;
	}

	public static float getZoomLevel() {
		return ZOOM_LEVEL;
	}
}
