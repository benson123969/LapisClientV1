package com.lapisclient.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lapisclient.LapisClient;
import com.lapisclient.module.Module;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private final File configFile;

	public ConfigManager() {
		File configDir = new File(FabricLoader.getInstance().getConfigDir().toFile(), "lapisclient");
		if (!configDir.exists()) {
			configDir.mkdirs();
		}
		this.configFile = new File(configDir, "config.json");
	}

	public void save() {
		try {
			JsonObject config = new JsonObject();
			JsonObject modules = new JsonObject();

			for (Module module : LapisClient.getInstance().getModuleManager().getModules()) {
				modules.add(module.getName(), module.toJson());
			}

			config.add("modules", modules);

			FileWriter writer = new FileWriter(configFile);
			GSON.toJson(config, writer);
			writer.close();

			LapisClient.LOGGER.info("Configuration saved successfully");
		} catch (IOException e) {
			LapisClient.LOGGER.error("Failed to save configuration", e);
		}
	}

	public void load() {
		if (!configFile.exists()) {
			LapisClient.LOGGER.info("No configuration file found, using defaults");
			return;
		}

		try {
			FileReader reader = new FileReader(configFile);
			JsonObject config = GSON.fromJson(reader, JsonObject.class);
			reader.close();

			if (config.has("modules")) {
				JsonObject modules = config.getAsJsonObject("modules");
				for (Module module : LapisClient.getInstance().getModuleManager().getModules()) {
					if (modules.has(module.getName())) {
						module.fromJson(modules.getAsJsonObject(module.getName()));
					}
				}
			}

			LapisClient.LOGGER.info("Configuration loaded successfully");
		} catch (Exception e) {
			LapisClient.LOGGER.error("Failed to load configuration", e);
		}
	}
}
