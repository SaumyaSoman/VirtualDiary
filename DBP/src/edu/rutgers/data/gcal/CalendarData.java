package edu.rutgers.data.gcal;

import java.util.List;


public class CalendarData {

	List<CalEvent> events;

	public List<CalEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CalEvent> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "CalendarData [events=" + events + "]";
	}

	

}
