package com.lapisclient;

import com.lapisclient.config.ConfigManager;
import com.lapisclient.module.ModuleManager;
import com.lapisclient.ui.ClickGuiScreen;
import com.lapisclient.youtube.YouTubeNotifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LapisClient implements ClientModInitializer {
	public static final String MOD_ID = "lapisclient";
	public static final String MOD_NAME = "Lapis Client";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	private static LapisClient instance;
	private ModuleManager moduleManager;
	private ConfigManager configManager;
	private YouTubeNotifier youTubeNotifier;

	public static KeyBinding menuKeybind;

	@Override
	public void onInitializeClient() {
		instance = this;
		LOGGER.info("Initializing " + MOD_NAME);

		// Initialize managers
		configManager = new ConfigManager();
		moduleManager = new ModuleManager();
		youTubeNotifier = new YouTubeNotifier();

		// Register keybindings
		menuKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.lapisclient.menu",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_9,
			"category.lapisclient"
		));

		// Register tick event for keybind checking
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (menuKeybind.wasPressed() && client.currentScreen == null) {
				client.setScreen(new ClickGuiScreen());
			}
		});

		// Load configuration
		configManager.load();

		// Start YouTube notifier
		youTubeNotifier.start();

		LOGGER.info(MOD_NAME + " initialized successfully!");
	}

	public static LapisClient getInstance() {
		return instance;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public YouTubeNotifier getYouTubeNotifier() {
		return youTubeNotifier;
	}
}
