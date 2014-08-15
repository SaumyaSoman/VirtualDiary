package edu.rutgers.data.fb;

import java.util.ArrayList;

public class FBPhoto {
	
	String url;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	@Override
	public String toString() {
		return "FBPhoto [url=" + url + ", location=" + location + ", time="
				+ time + ", people=" + people + ", lat=" + lat + ", lon=" + lon
				+ "]";
	}
	
	
}
