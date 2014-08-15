package edu.rutgers.model;

import edu.rutgers.util.Constants;

public class Mood {

	String type= Constants.NEU;
	double score=0;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Mood [type=" + type + ", score=" + score + "]";
	}
	
	
}
