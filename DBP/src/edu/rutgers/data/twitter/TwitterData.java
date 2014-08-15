package edu.rutgers.data.twitter;

import java.util.List;

public class TwitterData {
	List<Tweet> tweets;

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	@Override
	public String toString() {
		return "TwitterData [tweets=" + tweets + "]";
	}
	
}
