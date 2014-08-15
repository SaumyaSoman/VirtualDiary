package edu.rutgers.ws;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import edu.rutgers.util.Constants;

@XmlRootElement
public class WSResponse{
	
	String date;
	String mood=Constants.NEU;
	double score=0;
	int facebook=0;
	int twitter=0;
	int gcal=0;
	int foursquare=0;

	ArrayList<Events> events=new ArrayList<>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ArrayList<Events> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Events> events) {
		this.events = events;
	}
	
	public int getFacebook() {
		return facebook;
	}

	public void setFacebook(int facebook) {
		this.facebook = facebook;
	}

	public int getTwitter() {
		return twitter;
	}

	public void setTwitter(int twitter) {
		this.twitter = twitter;
	}

	public int getGcal() {
		return gcal;
	}

	public void setGcal(int gcal) {
		this.gcal = gcal;
	}

	public int getFoursquare() {
		return foursquare;
	}

	public void setFoursquare(int foursquare) {
		this.foursquare = foursquare;
	}

	
}
