package com.gmail.aaudevar.avajlauncher.aircrafts;

import com.gmail.aaudevar.avajlauncher.SimulationException;
import com.gmail.aaudevar.avajlauncher.aircrafts.Coordinates;

public class AircraftFactory {
	private static long id = 0;

	public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws SimulationException {

		if (p_type.toLowerCase().equals("baloon")) {
			return new Baloon(AircraftFactory.nextId(), p_name, p_coordinates);
		}
		else if (p_type.toLowerCase().equals("helicopter")) {
			return new Helicopter(AircraftFactory.nextId(), p_name, p_coordinates);
		}
		else if (p_type.toLowerCase().equals("jetplane")) {
			return new JetPlane(AircraftFactory.nextId(), p_name, p_coordinates);
		}
		else throw new SimulationException("Error: the type " + p_type + " doesn't exist");
	}

	public static Coordinates newCoordinates(int p_longitude, int p_latitude, int p_height) {
		return new Coordinates(p_longitude, p_latitude,p_height);
	}

	public static long nextId() {
		return id++;
	}
}