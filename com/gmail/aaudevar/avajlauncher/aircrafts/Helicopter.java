package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.Log;
import com.gmail.aaudevar.avajlauncher.WeatherTower;
import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {

	protected WeatherTower weatherTower;

	public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
		this.type = "Helicopter";
	}

	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinate);
		HashMap<String, String> weatherEffect = new HashMap<>();
		weatherEffect.put("RAIN","It's raining men ! Hallelujah!");
		weatherEffect.put("FOG","I kept bumping into ducks and chickens in the fog yesterday.  Fowl weather.");
		weatherEffect.put("SUN", "It's a beautiful day in the neighborhood, won't you be my neighbor ?");
		weatherEffect.put("SNOW", "Do you want to build a Snowman ?");

		if (weather.equals("RAIN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude()+ 5,
											this.coordinate.getLatitude(),
											this.coordinate.getHeight());
		}
		else if (weather.equals("FOG")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude()+ 1,
											this.coordinate.getLatitude(),
											this.coordinate.getHeight());
		}
		else if (weather.equals("SUN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude()+ 10,
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() + 2);
		}
		else if (weather.equals("SNOW")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() - 12);
		}
		Log.logLine(this.getId() + ": " + weatherEffect.get(weather));

		if (this.coordinate.getHeight() == 0) {
			Log.logLine(this.getId() + " landing.");
			this.weatherTower.unregister(this);
		}
	}

	public void registerTower(WeatherTower tower) {
		this.weatherTower = tower;
		this.weatherTower.register(this);
	}
}