package com.lapisclient.module.modules.hud;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.Collection;

public class PotionIndicatorModule extends Module {
	public PotionIndicatorModule() {
		super("Potion Indicator", "Display active potion effects", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = true;
		this.x = 10;
		this.y = 200;
		this.width = 150;
		this.height = 60;
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

		Collection<StatusEffectInstance> effects = client.player.getStatusEffects();
		if (effects.isEmpty()) return;

		int yOffset = (int) y;
		int maxWidth = 0;

		// Render background
		int effectCount = effects.size();
		int totalHeight = effectCount * 12 + 4;
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) x, (int) y, (int) (x + width), (int) (y + totalHeight), bgColor);
		}

		// Render each effect
		for (StatusEffectInstance effect : effects) {
			String effectName = effect.getEffectType().value().getName().getString();
			int duration = effect.getDuration() / 20; // Convert to seconds
			int minutes = duration / 60;
			int seconds = duration % 60;

			String text = String.format("%s %d:%02d", effectName, minutes, seconds);
			context.drawText(client.textRenderer, text, (int) x + 4, yOffset + 2, textColor.getRGB(), true);

			int textWidth = client.textRenderer.getWidth(text);
			if (textWidth > maxWidth) {
				maxWidth = textWidth;
			}

			yOffset += 12;
		}

		// Update dimensions
		this.width = maxWidth + 8;
		this.height = totalHeight;
	}
}
