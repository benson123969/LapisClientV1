package com.lapisclient.module.modules.performance;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class ParticleReducerModule extends Module {
	public ParticleReducerModule() {
		super("Particle Reducer", "Reduces particle effects for better FPS", ModuleCategory.PERFORMANCE);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
