package com.lapisclient.module.modules.performance;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class LowFireModule extends Module {
	public LowFireModule() {
		super("Low Fire", "Reduces fire overlay height", ModuleCategory.PERFORMANCE);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
