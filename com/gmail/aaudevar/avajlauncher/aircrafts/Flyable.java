package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.WeatherTower;

public interface Flyable {

	public abstract void updateConditions();
	public void registerTower(WeatherTower p_tower);

	String getId();
}