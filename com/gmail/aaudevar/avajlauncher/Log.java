package com.gmail.aaudevar.avajlauncher;

import java.io.*;

public class Log {

	private static BufferedWriter writer;
	private static File simulationFile;

	Log() {
		try {
			simulationFile = new File("simulation.txt");
			simulationFile.delete();
			if (writer == null)
				writer = new BufferedWriter(new FileWriter(simulationFile, true));
		} catch(IOException i) {
			System.out.println("Error: Can't create simulation.txt");
		}
	}

	public static void logLine(String str) {
		try {
			writer.write(str);
			writer.newLine();
			writer.flush();
		} catch(IOException i) {
			System.out.println("Errror: Can't write in simulation.txt");
		}
	}

	public static void closeFile() {
		try {
			if (writer != null)
				writer.close();
		} catch (IOException i) {
		System.out.println("Error: Can't close simulation.txt");
		}
	}
}