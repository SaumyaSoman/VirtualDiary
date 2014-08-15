package edu.rutgers.ws;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MoodResponse{

	ArrayList<Double> scores=new ArrayList<Double>();
	int facebook=0;
	
	int twitter=0;
	int gcal=0;
	int foursquare=0;

	int happy=0;
	int sad=0;
	int mixed=0;
	
	
	public int getHappy() {
		return happy;
	}

	public void setHappy(int happy) {
		this.happy = happy;
	}

	public int getSad() {
		return sad;
	}

	public void setSad(int sad) {
		this.sad = sad;
	}

	public int getMixed() {
		return mixed;
	}

	public void setMixed(int mixed) {
		this.mixed = mixed;
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

	public ArrayList<Double> getScores() {
		return scores;
	}

	public void setScores(ArrayList<Double> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "MoodResponse [scores=" + scores + ", facebook=" + facebook
				+ ", twitter=" + twitter + ", gcal=" + gcal + ", foursquare="
				+ foursquare + "]";
	}
	
	
	
}
