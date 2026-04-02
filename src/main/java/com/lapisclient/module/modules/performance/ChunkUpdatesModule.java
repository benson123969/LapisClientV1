package com.lapisclient.module.modules.performance;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class ChunkUpdatesModule extends Module {
	public ChunkUpdatesModule() {
		super("Smart Chunk Updates", "Optimizes chunk update rendering", ModuleCategory.PERFORMANCE);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
