package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.WeatherTower;

public class Aircraft implements Flyable{

	protected long			id;
	protected String		name;
	protected Coordinates	coordinate;
	protected String		type;
	protected WeatherTower weatherTower;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		this.id = p_id;
		this.name = p_name;
		this.coordinate = p_coordinates;
		this.type = "Default";
	}

	public String getId() {
		return (this.type + '#' + this.name + '(' + this.id + ')');
	}

	public void registerTower(WeatherTower tower) {
		this.weatherTower = tower;
		this.weatherTower.register(this);
	}

	@Override
	public void updateConditions() {
		// to be implemented by the child classes
	}
}