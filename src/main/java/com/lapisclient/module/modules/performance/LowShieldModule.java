package com.lapisclient.module.modules.performance;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class LowShieldModule extends Module {
	public LowShieldModule() {
		super("Low Shield", "Reduces shield overlay", ModuleCategory.PERFORMANCE);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
