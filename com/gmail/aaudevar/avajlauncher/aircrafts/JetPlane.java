package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.Log;
import com.gmail.aaudevar.avajlauncher.WeatherTower;
import java.util.HashMap;

public class JetPlane extends Aircraft {


	public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
		this.type = "JetPlane";
	}

	@Override
	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinate);
		HashMap<String, String> weatherEffect = new HashMap<>();
		weatherEffect.put("RAIN","Dangerous condition, call Tom Cruise! Fast!");
		weatherEffect.put("FOG","Do a barrel roll in the fog!");
		weatherEffect.put("SUN", "Fuck! I didn't bring any sunscreen.");
		weatherEffect.put("SNOW", "Flake it till you make it.");

		if (weather.equals("RAIN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude() + 5,
											this.coordinate.getHeight());
		}
		else if (weather.equals("FOG")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude() + 1,
											this.coordinate.getHeight());
		}
		else if (weather.equals("SUN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude() + 10,
											this.coordinate.getHeight() + 2);
		}
		else if (weather.equals("SNOW")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() - 7);
		}
		Log.logLine(this.getId() + ": " + weatherEffect.get(weather));

		if (this.coordinate.getHeight() == 0) {
			Log.logLine(this.getId() + " landing.");
			this.weatherTower.unregister(this);
		}
	}
}