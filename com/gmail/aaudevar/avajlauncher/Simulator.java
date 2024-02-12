package com.gmail.aaudevar.avajlauncher;

import com.gmail.aaudevar.avajlauncher.aircrafts.AircraftFactory;
import com.gmail.aaudevar.avajlauncher.aircrafts.Flyable;
import com.gmail.aaudevar.avajlauncher.aircrafts.Coordinates;

import java.io.*;
import java.util.ArrayList;

public class Simulator {

	private BufferedReader reader;
	private Log log = new Log();
	private WeatherTower tower;
	private ArrayList<Flyable> aircrafts = new ArrayList<>();

	private Simulator(){	
	}

	private void checkFile(String file) throws SimulationException, IOException {

		reader = new BufferedReader(new FileReader(file));
		String line;
		int lineCount = 0;

		line = reader.readLine();
		lineCount++;
		if (line == null)
			throw new SimulationException("Error: The Simulation file is empty.");
		if (line.length() > 0) {
			try {
				line = line.trim();
				tower = new WeatherTower(Integer.parseInt(line));
			} catch (NumberFormatException e) {
				throw new SimulationException("Error: The first line must be a positive Integer");
			}
		}
		else
			throw new SimulationException("Error: The first line is empty.");
		while (line != null){
			line = reader.readLine();
			if (line == null || line.length() == 0)
				break;
			lineCount++;

			String split[] = line.split(" ");
			if (split.length != 5)
				throw new SimulationException("Error: line #" + lineCount + " the format must be: TYPE NAME LONGITUDE LATITUDE HEIGHT");
			try {
				int coordinates[] = { Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4])};
				for (int coordinate : coordinates) {
					if (coordinate < 1)
					throw new SimulationException("Error: line #" + lineCount + " LONGITUDE LATITUDE HEIGHT must be positive Integer");
				}
				Coordinates c = AircraftFactory.newCoordinates(coordinates[0], coordinates[1], coordinates[2]);
				aircrafts.add(AircraftFactory.newAircraft(split[0], split[1], c));
			} catch (NumberFormatException e) {
				throw new SimulationException("Error: line #" + lineCount + " LONGITUDE LATITUDE HEIGHT must be positive Integer");
			}
		}
		if (lineCount < 2)
			throw new SimulationException("Error: The following lines must describes an aircraft");
		reader.close();
	}

	private void simulation(Simulator simulator) {

		for (Flyable f : simulator.aircrafts)
			f.registerTower(simulator.tower);
		
		while (simulator.tower.getSimCount() < simulator.tower.getTotalSim()) {
			log.logLine("|- Simulation: " + (simulator.tower.getSimCount() + 1) + " -|");
			simulator.tower.changeWeather();
			simulator.tower.countSimulation();
		}
	}

	public static void main(String[] args){
		try {
			if (args.length != 1 )
				throw new SimulationException("Error: Usage is: java Simulator File");
			Simulator simulator = new Simulator();
			simulator.checkFile(args[0]);
			if (simulator.aircrafts.size() == 0)
				throw new SimulationException("Error: No aircrafts in the scenario file.");
			simulator.simulation(simulator);
		} catch (SimulationException e) {
			System.out.println(e.getMessage());
			SimulationException.printFormat();
		} catch (IOException e) {
			System.out.println("Error: Can't open the file"  + args[0]);
		} finally {
			Log.closeFile();
		}
	}


}


