package com.lapisclient.module;

import com.google.gson.JsonObject;

import java.awt.Color;

public abstract class Module {
	protected String name;
	protected String description;
	protected ModuleCategory category;
	protected boolean enabled;
	protected boolean draggable;

	// Position for draggable modules
	protected float x;
	protected float y;
	protected float width;
	protected float height;

	// Visual settings
	protected Color backgroundColor;
	protected Color textColor;
	protected boolean showBackground;
	protected float backgroundOpacity;

	public Module(String name, String description, ModuleCategory category) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.enabled = false;
		this.draggable = false;

		// Default position
		this.x = 10;
		this.y = 10;
		this.width = 100;
		this.height = 20;

		// Default visual settings
		this.backgroundColor = new Color(0, 0, 0, 100);
		this.textColor = new Color(255, 255, 255, 255);
		this.showBackground = true;
		this.backgroundOpacity = 0.5f;
	}

	public abstract void onEnable();
	public abstract void onDisable();
	public abstract void onRender(float delta);

	public void toggle() {
		enabled = !enabled;
		if (enabled) {
			onEnable();
		} else {
			onDisable();
		}
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("name", name);
		json.addProperty("enabled", enabled);
		json.addProperty("x", x);
		json.addProperty("y", y);
		json.addProperty("showBackground", showBackground);
		json.addProperty("backgroundOpacity", backgroundOpacity);
		json.addProperty("backgroundColor", backgroundColor.getRGB());
		json.addProperty("textColor", textColor.getRGB());
		return json;
	}

	public void fromJson(JsonObject json) {
		if (json.has("enabled")) enabled = json.get("enabled").getAsBoolean();
		if (json.has("x")) x = json.get("x").getAsFloat();
		if (json.has("y")) y = json.get("y").getAsFloat();
		if (json.has("showBackground")) showBackground = json.get("showBackground").getAsBoolean();
		if (json.has("backgroundOpacity")) backgroundOpacity = json.get("backgroundOpacity").getAsFloat();
		if (json.has("backgroundColor")) backgroundColor = new Color(json.get("backgroundColor").getAsInt(), true);
		if (json.has("textColor")) textColor = new Color(json.get("textColor").getAsInt(), true);
	}

	// Getters and setters
	public String getName() { return name; }
	public String getDescription() { return description; }
	public ModuleCategory getCategory() { return category; }
	public boolean isEnabled() { return enabled; }
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	public boolean isDraggable() { return draggable; }
	public void setDraggable(boolean draggable) { this.draggable = draggable; }
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	public float getWidth() { return width; }
	public void setWidth(float width) { this.width = width; }
	public float getHeight() { return height; }
	public void setHeight(float height) { this.height = height; }
	public Color getBackgroundColor() { return backgroundColor; }
	public void setBackgroundColor(Color color) { this.backgroundColor = color; }
	public Color getTextColor() { return textColor; }
	public void setTextColor(Color color) { this.textColor = color; }
	public boolean isShowBackground() { return showBackground; }
	public void setShowBackground(boolean show) { this.showBackground = show; }
	public float getBackgroundOpacity() { return backgroundOpacity; }
	public void setBackgroundOpacity(float opacity) { this.backgroundOpacity = opacity; }
}
