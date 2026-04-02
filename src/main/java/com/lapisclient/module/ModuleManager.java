package com.lapisclient.module;

import com.lapisclient.module.modules.hud.*;
import com.lapisclient.module.modules.performance.*;
import com.lapisclient.module.modules.render.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
	private final List<Module> modules = new ArrayList<>();

	public ModuleManager() {
		// Register HUD modules
		registerModule(new ArmorHudModule());
		registerModule(new PotionIndicatorModule());
		registerModule(new FpsIndicatorModule());
		registerModule(new PingIndicatorModule());
		registerModule(new CoordinatesModule());
		registerModule(new DirectionModule());

		// Register Performance modules
		registerModule(new LowFireModule());
		registerModule(new LowShieldModule());
		registerModule(new ParticleReducerModule());
		registerModule(new EntityCullingModule());
		registerModule(new ChunkUpdatesModule());

		// Register Render modules
		registerModule(new ZoomModule());
		registerModule(new FullbrightModule());
	}

	private void registerModule(Module module) {
		modules.add(module);
	}

	public List<Module> getModules() {
		return modules;
	}

	public List<Module> getModulesByCategory(ModuleCategory category) {
		return modules.stream()
			.filter(m -> m.getCategory() == category)
			.collect(Collectors.toList());
	}

	public Module getModuleByName(String name) {
		return modules.stream()
			.filter(m -> m.getName().equalsIgnoreCase(name))
			.findFirst()
			.orElse(null);
	}

	public List<Module> getEnabledModules() {
		return modules.stream()
			.filter(Module::isEnabled)
			.collect(Collectors.toList());
	}
}
