package com.gmail.aaudevar.avajlauncher;

public class SimulationException extends Exception {

	public SimulationException(String str) {
		super(str);
	}

	public static void printFormat() {
		System.out.println("The scenario file must have this format:");
		System.out.println("First line: Number of time the simulations is run.");
		System.out.println("Following lines: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
	}
}