package com.gmail.aaudevar.avajlauncher;

import com.gmail.aaudevar.avajlauncher.aircrafts.Coordinates;
import java.util.Random;

public class WeatherProvider {

	static private WeatherProvider weatherProvider = null;
	static private final String[] weather = { "RAIN", "FOG", "SUN", "SNOW"};

	private WeatherProvider() {
	}

	static public WeatherProvider getProvider() {
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int ran = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + new Random().nextInt(100)) % 4;
		return (weather[ran]);
	}
}