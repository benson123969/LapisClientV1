package com.lapisclient.ui;

import com.lapisclient.LapisClient;
import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;
import com.lapisclient.module.modules.hud.ArmorHudModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
	private static final int CATEGORY_WIDTH = 120;
	private static final int CATEGORY_HEIGHT = 300;
	private static final int MODULE_HEIGHT = 20;
	private static final int PADDING = 5;

	private final List<CategoryPanel> categoryPanels;
	private Module selectedModule;
	private boolean draggingModule;

	public ClickGuiScreen() {
		super(Text.literal("Lapis Client"));
		this.categoryPanels = new ArrayList<>();

		// Create category panels
		int x = 20;
		for (ModuleCategory category : ModuleCategory.values()) {
			categoryPanels.add(new CategoryPanel(category, x, 20));
			x += CATEGORY_WIDTH + 10;
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);

		// Render title
		context.drawCenteredTextWithShadow(this.textRenderer, "§b§lLapis Client", width / 2, 5, 0xFFFFFF);

		// Render category panels
		for (CategoryPanel panel : categoryPanels) {
			panel.render(context, mouseX, mouseY);
		}

		// Render settings panel if module is selected
		if (selectedModule != null) {
			renderSettingsPanel(context, mouseX, mouseY);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		// Check if clicking on a category panel
		for (CategoryPanel panel : categoryPanels) {
			if (panel.mouseClicked(mouseX, mouseY, button)) {
				return true;
			}
		}

		// Check if clicking on settings panel
		if (selectedModule != null && button == 1) { // Right click
			selectedModule = null;
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		draggingModule = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public void close() {
		super.close();
		// Save configuration when closing
		LapisClient.getInstance().getConfigManager().save();
	}

	private void renderSettingsPanel(DrawContext context, int mouseX, int mouseY) {
		int panelX = width - 250;
		int panelY = 20;
		int panelWidth = 230;
		int panelHeight = 400;

		// Background
		context.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, 0xAA000000);

		// Title
		String title = "§e" + selectedModule.getName() + " Settings";
		context.drawText(this.textRenderer, title, panelX + 5, panelY + 5, 0xFFFFFF, true);

		int y = panelY + 25;

		// Common settings
		context.drawText(this.textRenderer, "§7Show Background", panelX + 10, y, 0xFFFFFF, false);
		context.drawText(this.textRenderer, selectedModule.isShowBackground() ? "§aON" : "§cOFF", panelX + panelWidth - 30, y, 0xFFFFFF, false);
		y += 20;

		// Module-specific settings
		if (selectedModule instanceof ArmorHudModule) {
			ArmorHudModule armor = (ArmorHudModule) selectedModule;

			context.drawText(this.textRenderer, "§7Show Percentage", panelX + 10, y, 0xFFFFFF, false);
			context.drawText(this.textRenderer, armor.isShowPercentage() ? "§aON" : "§cOFF", panelX + panelWidth - 30, y, 0xFFFFFF, false);
			y += 20;

			context.drawText(this.textRenderer, "§7Show Durability", panelX + 10, y, 0xFFFFFF, false);
			context.drawText(this.textRenderer, armor.isShowDurability() ? "§aON" : "§cOFF", panelX + panelWidth - 30, y, 0xFFFFFF, false);
			y += 20;

			context.drawText(this.textRenderer, "§7Color by Durability", panelX + 10, y, 0xFFFFFF, false);
			context.drawText(this.textRenderer, armor.isColorBasedOnDurability() ? "§aON" : "§cOFF", panelX + panelWidth - 30, y, 0xFFFFFF, false);
			y += 20;
		}

		// Instructions
		y = panelY + panelHeight - 40;
		context.drawText(this.textRenderer, "§7Left click: Toggle setting", panelX + 10, y, 0xAAAAAA, false);
		y += 12;
		context.drawText(this.textRenderer, "§7Right click: Close settings", panelX + 10, y, 0xAAAAAA, false);
	}

	class CategoryPanel {
		private final ModuleCategory category;
		private final int x, y;
		private final List<Module> modules;
		private boolean expanded = true;

		public CategoryPanel(ModuleCategory category, int x, int y) {
			this.category = category;
			this.x = x;
			this.y = y;
			this.modules = LapisClient.getInstance().getModuleManager().getModulesByCategory(category);
		}

		public void render(DrawContext context, int mouseX, int mouseY) {
			// Header
			int headerColor = new Color(30, 30, 30, 200).getRGB();
			context.fill(x, y, x + CATEGORY_WIDTH, y + MODULE_HEIGHT, headerColor);
			context.drawCenteredTextWithShadow(textRenderer, "§l" + category.getName(), x + CATEGORY_WIDTH / 2, y + 6, 0xFFFFFF);

			if (!expanded) return;

			// Modules
			int moduleY = y + MODULE_HEIGHT;
			for (Module module : modules) {
				boolean hovered = mouseX >= x && mouseX <= x + CATEGORY_WIDTH &&
								  mouseY >= moduleY && mouseY <= moduleY + MODULE_HEIGHT;

				int bgColor = module.isEnabled() ?
					new Color(50, 150, 50, 180).getRGB() :
					new Color(50, 50, 50, 180).getRGB();

				if (hovered) {
					bgColor = module.isEnabled() ?
						new Color(60, 180, 60, 200).getRGB() :
						new Color(70, 70, 70, 200).getRGB();
				}

				context.fill(x, moduleY, x + CATEGORY_WIDTH, moduleY + MODULE_HEIGHT, bgColor);
				context.drawText(textRenderer, module.getName(), x + PADDING, moduleY + 6, 0xFFFFFF, true);

				moduleY += MODULE_HEIGHT;
			}
		}

		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			// Check header click
			if (mouseX >= x && mouseX <= x + CATEGORY_WIDTH && mouseY >= y && mouseY <= y + MODULE_HEIGHT) {
				if (button == 0) {
					expanded = !expanded;
					return true;
				}
			}

			if (!expanded) return false;

			// Check module clicks
			int moduleY = y + MODULE_HEIGHT;
			for (Module module : modules) {
				if (mouseX >= x && mouseX <= x + CATEGORY_WIDTH &&
					mouseY >= moduleY && mouseY <= moduleY + MODULE_HEIGHT) {

					if (button == 0) {
						// Left click: toggle module
						module.toggle();
						return true;
					} else if (button == 1) {
						// Right click: open settings
						selectedModule = module;
						return true;
					}
				}
				moduleY += MODULE_HEIGHT;
			}

			return false;
		}
	}
}
