package com.lapisclient.module.modules.hud;

import com.google.gson.JsonObject;
import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ArmorHudModule extends Module {
	private boolean showPercentage = true;
	private boolean showDurability = true;
	private boolean colorBasedOnDurability = true;

	public ArmorHudModule() {
		super("Armor HUD", "Display armor durability", ModuleCategory.HUD);
		this.draggable = true;
		this.enabled = true;
		this.x = 10;
		this.y = 100;
		this.width = 80;
		this.height = 80;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null) return;

		PlayerEntity player = client.player;
		DrawContext context = new DrawContext(client, client.getBufferBuilders().getEntityVertexConsumers());

		// This will be rendered through the mixin
	}

	public void render(DrawContext context, int scaledWidth, int scaledHeight) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null || !enabled) return;

		PlayerEntity player = client.player;
		int yOffset = (int) y;

		// Render background if enabled
		if (showBackground) {
			int bgColor = backgroundColor.getRGB();
			context.fill((int) x, (int) y, (int) (x + width), (int) (y + height), bgColor);
		}

		// Render armor pieces
		for (int i = 0; i < 4; i++) {
			ItemStack armorPiece = player.getInventory().getArmorStack(3 - i);
			if (!armorPiece.isEmpty()) {
				int itemY = yOffset + (i * 20);

				// Render item
				context.drawItem(armorPiece, (int) x + 2, itemY);

				// Calculate durability
				if (armorPiece.isDamageable()) {
					int maxDurability = armorPiece.getMaxDamage();
					int currentDurability = maxDurability - armorPiece.getDamage();
					float percentage = (float) currentDurability / maxDurability * 100;

					// Choose color based on durability
					int color = textColor.getRGB();
					if (colorBasedOnDurability) {
						if (percentage > 66) {
							color = 0xFF00FF00; // Green
						} else if (percentage > 33) {
							color = 0xFFFFAA00; // Orange
						} else {
							color = 0xFFFF0000; // Red
						}
					}

					String text = "";
					if (showPercentage && showDurability) {
						text = String.format("%d (%.0f%%)", currentDurability, percentage);
					} else if (showPercentage) {
						text = String.format("%.0f%%", percentage);
					} else if (showDurability) {
						text = String.format("%d", currentDurability);
					}

					if (!text.isEmpty()) {
						context.drawText(client.textRenderer, text, (int) x + 22, itemY + 6, color, true);
					}
				}
			}
		}
	}

	@Override
	public JsonObject toJson() {
		JsonObject json = super.toJson();
		json.addProperty("showPercentage", showPercentage);
		json.addProperty("showDurability", showDurability);
		json.addProperty("colorBasedOnDurability", colorBasedOnDurability);
		return json;
	}

	@Override
	public void fromJson(JsonObject json) {
		super.fromJson(json);
		if (json.has("showPercentage")) showPercentage = json.get("showPercentage").getAsBoolean();
		if (json.has("showDurability")) showDurability = json.get("showDurability").getAsBoolean();
		if (json.has("colorBasedOnDurability")) colorBasedOnDurability = json.get("colorBasedOnDurability").getAsBoolean();
	}

	// Getters and setters
	public boolean isShowPercentage() { return showPercentage; }
	public void setShowPercentage(boolean show) { this.showPercentage = show; }
	public boolean isShowDurability() { return showDurability; }
	public void setShowDurability(boolean show) { this.showDurability = show; }
	public boolean isColorBasedOnDurability() { return colorBasedOnDurability; }
	public void setColorBasedOnDurability(boolean color) { this.colorBasedOnDurability = color; }
}
