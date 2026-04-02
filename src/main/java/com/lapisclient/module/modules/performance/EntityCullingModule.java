package com.lapisclient.module.modules.performance;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class EntityCullingModule extends Module {
	public EntityCullingModule() {
		super("Entity Culling", "Don't render entities that aren't visible", ModuleCategory.PERFORMANCE);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
