package com.lapisclient.module.modules.hud;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class DirectionModule extends Module {
	public DirectionModule() {
		super("Direction", "Display facing direction", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = false;
		this.x = 10;
		this.y = 85;
		this.width = 100;
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

		float yaw = client.player.getYaw();
		String direction = getDirection(yaw);

		// Render background
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), bgColor);
		}

		// Render direction
		String text = "Facing: " + direction;
		context.drawText(client.textRenderer, text, (int) x + 4, (int) y + 6, textColor.getRGB(), true);

		// Update width based on text
		this.width = client.textRenderer.getWidth(text) + 8;
	}

	private String getDirection(float yaw) {
		yaw = MathHelper.wrapDegrees(yaw);

		if (yaw < -157.5 || yaw >= 157.5) return "North";
		if (yaw >= -157.5 && yaw < -112.5) return "North East";
		if (yaw >= -112.5 && yaw < -67.5) return "East";
		if (yaw >= -67.5 && yaw < -22.5) return "South East";
		if (yaw >= -22.5 && yaw < 22.5) return "South";
		if (yaw >= 22.5 && yaw < 67.5) return "South West";
		if (yaw >= 67.5 && yaw < 112.5) return "West";
		if (yaw >= 112.5 && yaw < 157.5) return "North West";

		return "Unknown";
	}
}
