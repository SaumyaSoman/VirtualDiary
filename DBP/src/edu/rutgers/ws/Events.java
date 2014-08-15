package edu.rutgers.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Events {
	
	String message;
	String location;
	String time;
	String source;
	String mood;
	Tags tags=new Tags();	
	String rsvp_status;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public Tags getTags() {
		return tags;
	}
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	public String getRsvp_status() {
		return rsvp_status;
	}
	public void setRsvp_status(String rsvp_status) {
		this.rsvp_status = rsvp_status;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	
	
	
}
