package edu.rutgers.data.fb;

import edu.rutgers.model.Mood;


public class FBPost {

	String people;
	String time;
	String message;
	Mood mood;
	
	
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

	/**
	 * @return the people
	 */
	public String getPeople() {
		return people;
	}

	/**
	 * @param people the people to set
	 */
	public void setPeople(String people) {
		this.people = people;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FBPost [people=" + people + ", time=" + time + "]";
	}
	
}
