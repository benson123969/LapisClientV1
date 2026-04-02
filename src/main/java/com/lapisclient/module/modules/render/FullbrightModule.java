package com.lapisclient.module.modules.render;

import com.lapisclient.module.Module;
import com.lapisclient.module.ModuleCategory;

public class FullbrightModule extends Module {
	public FullbrightModule() {
		super("Fullbright", "Maximum brightness at all times", ModuleCategory.RENDER);
		this.enabled = false;
	}

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {}

	@Override
	public void onRender(float delta) {}
}
