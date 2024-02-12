package com.gmail.aaudevar.avajlauncher;

import com.gmail.aaudevar.avajlauncher.aircrafts.Flyable;
import java.util.List;
import java.util.ArrayList;

public class Tower {

	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable p_flyable) {
		if (!observers.contains(p_flyable)) {
			observers.add(p_flyable);
			Log.logLine("Tower says: " + p_flyable.getId() + " registered to weather tower.");
		}
	}

	public void unregister(Flyable p_flyable) {
		if (observers.contains(p_flyable)) {
			observers.remove(p_flyable);
			Log.logLine("Tower says: " + p_flyable.getId() + " unregistered to weather tower.");
		}
	}

	protected void conditionsChanged() {
		for (int i = 0; i < observers.size(); i++)
			observers.get(i).updateConditions();
	}
}