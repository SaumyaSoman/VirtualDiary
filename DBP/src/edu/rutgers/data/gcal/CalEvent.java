package edu.rutgers.data.gcal;

import java.util.ArrayList;

public class CalEvent {

	String message;
	String time;
	String source="googlecalendar";
	ArrayList<String> tags=new ArrayList<String>();	
	long lat=0;
	 long lon=0;
	
	 
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public long getLon() {
		return lon;
	}
	public void setLon(long lon) {
		this.lon = lon;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "CalEvent [message=" + message + ", time=" + time + ", source="
				+ source + ", tags=" + tags + "]";
	}
	
}
