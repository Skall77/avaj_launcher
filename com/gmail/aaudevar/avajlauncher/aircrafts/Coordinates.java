package com.gmail.aaudevar.avajlauncher.aircrafts;

public class Coordinates {

	private int longitude;
	private int latitude;
	private int height;

	Coordinates(int p_longitude, int p_latitude, int p_height) {
		if (p_longitude < 0)
			this.longitude = 0;
		else
			this.longitude = p_longitude;
		
		if (p_latitude < 0)
			this.latitude = 0;
		else
			this.latitude = p_latitude;
		
		if (p_height > 100)
			this.height = 100;
		else if (p_height < 0)
			this.height = 0;
		else
			this.height = p_height;
	}

	public int getLongitude() {
		return this.longitude;
	}

	public int getLatitude() {
		return this.latitude;
	}

	public int getHeight() {
		return this.height;
	}

}