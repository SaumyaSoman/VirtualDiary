package edu.rutgers.data.twitter;

import edu.rutgers.model.Mood;

public class Tweet {
	String message;	
	String time;
	Mood mood;
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
	
	
	public Mood getMood() {
		return mood;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
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
	@Override
	public String toString() {
		return "Tweet [message=" + message + ", time=" + time + "]";
	}
	
	
}
