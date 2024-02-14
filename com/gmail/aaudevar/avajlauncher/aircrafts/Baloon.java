package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.Log;
import com.gmail.aaudevar.avajlauncher.WeatherTower;
import java.util.HashMap;

public class Baloon extends Aircraft {

	public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
		this.type = "Baloon";
	}

	@Override
	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinate);
		HashMap<String, String> weatherEffect = new HashMap<>();
		weatherEffect.put("RAIN","Tis but a scratch!");
		weatherEffect.put("FOG","What's Fog to a God ?");
		weatherEffect.put("SUN", "Time to go as high as possible before we eventually have to go down again");
		weatherEffect.put("SNOW", "Okay, we're absolutely fucked!");

		if (weather.equals("RAIN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() - 5);
		}
		else if (weather.equals("FOG")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() - 3);
		}
		else if (weather.equals("SUN")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude()+ 2,
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() + 4);
		}
		else if (weather.equals("SNOW")) {
			this.coordinate = new Coordinates(this.coordinate.getLongitude(),
											this.coordinate.getLatitude(),
											this.coordinate.getHeight() - 15);
		}
		Log.logLine(this.getId() + ": " + weatherEffect.get(weather));

		if (this.coordinate.getHeight() == 0) {
			Log.logLine(this.getId() + " landing.");
			this.weatherTower.unregister(this);
		}
	}
}