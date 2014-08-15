package edu.rutgers.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.rutgers.data.fb.FBData;
import edu.rutgers.data.fb.FBEvent;
import edu.rutgers.data.fb.FBPhoto;
import edu.rutgers.data.fb.FBPost;
import edu.rutgers.data.fb.FBStatus;
import edu.rutgers.data.twitter.Tweet;
import edu.rutgers.data.twitter.TwitterData;
import edu.rutgers.model.Facebook;
import edu.rutgers.model.Twitter;
import edu.rutgers.util.DBUtil;
import edu.rutgers.util.SentimentClassifier;

public class TwitterDAO {

	String date;

	public static void main (String args[]) throws ParseException, JSONException{

		TwitterDAO dao=new TwitterDAO();
		String date="2014-04-11";
		dao.getData("5343633d87677b24604dada5",date).toString();
	}

	public TwitterData getData(String userId,String date) throws ParseException, JSONException {
		// TODO Auto-generated method stub
		this.date=date;
		TwitterData twitterData=new TwitterData();
		DateFormat targetFormat = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
		DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = originalFormat.parse(date);
		String newDate = targetFormat.format(date1); 
		System.out.println(newDate);
		List<Twitter> twitterTweetData=DBUtil.getTwitterData(userId,"TWEET",newDate);
		List<Tweet> tweets= processTweetData(twitterTweetData);		

		twitterData.setTweets(tweets);

		return twitterData;
	}

	private List<Tweet> processTweetData(List<Twitter> twitterTweetData) throws ParseException {
		// TODO Auto-generated method stub
		List<Tweet> list=new ArrayList<Tweet>();
		try {
			for (Twitter twitter : twitterTweetData) {
				if(twitter.getData() != null)
				{
					JSONObject tweetJson=new JSONObject(twitter.getData());
					String text=tweetJson.getString("text");

					String dateString=tweetJson.getString("created_at");

					DateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
					DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = originalFormat.parse(dateString);
					String date = targetFormat.format(date1);  

					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
					String time = timeFormat.format(date1);
					if(text != "" && date.equals(this.date)){						

						Tweet tweet=new Tweet();

						tweet.setMessage(text);						
						tweet.setTime(time);	
						System.out.println(tweet.getMessage());
						tweet.setMood(SentimentClassifier.classify(tweet.getMessage()));
						list.add(tweet);
					}
				}	
			}
			return list;
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
