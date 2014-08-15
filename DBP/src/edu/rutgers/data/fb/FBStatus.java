package edu.rutgers.data.fb;

import java.util.ArrayList;

import edu.rutgers.model.Mood;
import edu.rutgers.util.Constants;

public class FBStatus {

	String message;
	Mood mood;
	String location;
	String time;
	ArrayList<String> people;
	double lat=0;
	 double lon=0;
	
	 
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the people
	 */
	public ArrayList<String> getPeople() {
		return people;
	}
	/**
	 * @param people the people to set
	 */
	public void setPeople(ArrayList<String> people) {
		this.people = people;
	}
	public Mood getMood() {
		return mood;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
	
	
	
}
