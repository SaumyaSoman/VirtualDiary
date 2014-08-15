package edu.rutgers.data.fsq;

import java.util.ArrayList;

import edu.rutgers.model.Mood;
import edu.rutgers.ws.Tags;

public class FSQEvent {
	
	String message;
	String location;
	String time;
	String source="foursquare";
	Mood mood=new Mood();
	ArrayList<String> tags=new ArrayList<String>();	
	String pic_url;
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public Mood getMood() {
		return mood;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	@Override
	public String toString() {
		return "FSQEvent [message=" + message + ", location=" + location
				+ ", time=" + time + ", source=" + source + ", mood=" + mood
				+ ", tags=" + tags + ", pic_url=" + pic_url + ", lat=" + lat
				+ ", lon=" + lon + "]";
	}
	

}
