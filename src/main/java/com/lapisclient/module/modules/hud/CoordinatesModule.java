package com.lapisclient.module.modules.hud;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordinatesModule extends Module {
	public CoordinatesModule() {
		super("Coordinates", "Display player coordinates", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = false;
		this.x = 10;
		this.y = 60;
		this.width = 120;
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
		if (client.player == null) return;

		int x = (int) client.player.getX();
		int y = (int) client.player.getY();
		int z = (int) client.player.getZ();

		// Render background
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) this.x, (int) this.y, (int) (this.x + width), (int) (this.y + height), bgColor);
		}

		// Render coordinates
		String text = String.format("XYZ: %d, %d, %d", x, y, z);
		context.drawText(client.textRenderer, text, (int) this.x + 4, (int) this.y + 6, textColor.getRGB(), true);

		// Update width based on text
		this.width = client.textRenderer.getWidth(text) + 8;
	}
}
