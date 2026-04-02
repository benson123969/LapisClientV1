package com.lapisclient.module.modules.hud;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsIndicatorModule extends Module {
	public FpsIndicatorModule() {
		super("FPS Indicator", "Display current FPS", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = true;
		this.x = 10;
		this.y = 10;
		this.width = 60;
		this.height = 20;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}

	public void render(DrawContext context, int scaledWidth, int scaledHeight) {
		if (!enabled) return;

		MinecraftClient client = MinecraftClient.getInstance();
		int fps = client.getCurrentFps();

		// Render background
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), bgColor);
		}

		// Render FPS text
		String text = "FPS: " + fps;
		context.drawText(client.textRenderer, text, (int) x + 4, (int) y + 6, textColor.getRGB(), true);

		// Update width based on text
		this.width = client.textRenderer.getWidth(text) + 8;
	}
}
