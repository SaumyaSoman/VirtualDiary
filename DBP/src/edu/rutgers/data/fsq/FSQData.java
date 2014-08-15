package edu.rutgers.data.fsq;

import java.util.List;

import edu.rutgers.data.fb.FBEvent;

public class FSQData {

	List<FSQEvent> events;

	public List<FSQEvent> getEvents() {
		return events;
	}

	public void setEvents(List<FSQEvent> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "FSQData [events=" + events + "]";
	}
	
	
}
