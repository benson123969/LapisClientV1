package com.lapisclient.module;

public enum ModuleCategory {
	HUD("HUD"),
	RENDER("Render"),
	PERFORMANCE("Performance"),
	COMBAT("Combat"),
	MOVEMENT("Movement"),
	MISC("Misc");

	private final String name;

	ModuleCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
