package com.gmail.aaudevar.avajlauncher;

import com.gmail.aaudevar.avajlauncher.aircrafts.Coordinates;

public class WeatherTower extends Tower {

	private int totalSimulations;
	private int simulationsCounter;

	WeatherTower(int totalSim) throws NumberFormatException {
		if (totalSim < 0)
			throw new NumberFormatException();
		this.totalSimulations = totalSim;
		this.simulationsCounter = 0;
	}

	public int getTotalSim() {	return this.totalSimulations; }
	public int getSimCount() {	return this.simulationsCounter; }
	public void countSimulation() { this.simulationsCounter++; }

	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	public void changeWeather() {
		this.conditionsChanged();
	}
}