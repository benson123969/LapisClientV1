package com.lapisclient.module.modules.hud;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

public class PingIndicatorModule extends Module {
	public PingIndicatorModule() {
		super("Ping Indicator", "Display current ping", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = true;
		this.x = 10;
		this.y = 35;
		this.width = 80;
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

		int ping = 0;
		PlayerListEntry playerListEntry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
		if (playerListEntry != null) {
			ping = playerListEntry.getLatency();
		}

		// Render background
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), bgColor);
		}

		// Color based on ping
		int color = textColor.getRGB();
		if (ping < 50) {
			color = 0xFF00FF00; // Green
		} else if (ping < 100) {
			color = 0xFFFFAA00; // Orange
		} else {
			color = 0xFFFF0000; // Red
		}

		// Render ping text
		String text = "Ping: " + ping + "ms";
		context.drawText(client.textRenderer, text, (int) x + 4, (int) y + 6, color, true);

		// Update width based on text
		this.width = client.textRenderer.getWidth(text) + 8;
	}
}
