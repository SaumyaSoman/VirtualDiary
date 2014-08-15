package edu.rutgers.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.rutgers.data.fb.FBData;
import edu.rutgers.data.fb.FBEvent;
import edu.rutgers.data.fb.FBPhoto;
import edu.rutgers.data.fb.FBPost;
import edu.rutgers.data.fb.FBStatus;
import edu.rutgers.model.Facebook;
import edu.rutgers.util.DBUtil;
import edu.rutgers.util.SentimentClassifier;


public class FacebookDAO {

	String date;

	public static void main (String args[]) throws ParseException, JSONException{

		FacebookDAO dao=new FacebookDAO();
		String dateString="2013-09-02";
		System.out.println(dao.getData(dateString, "534333f195959b1cb0cc52d4"));
	}

	public FBData getData(String date, String userid) throws ParseException, JSONException {
		// TODO Auto-generated method stub
		this.date=date;
		FBData fbdata=new FBData();

		List<Facebook> fbData=DBUtil.getFbData(userid,"EVENT",date);
		List<FBEvent> events= processEventData(fbData);		

		List<Facebook> statusData=DBUtil.getFbData(userid,"STATUS",date);
		List<FBStatus> status= processStatusData(statusData);

		List<Facebook> photosData=DBUtil.getFbData(userid,"PHOTO",date);
		List<FBPhoto> photos= processPhotoData(photosData);

		List<Facebook> postsData=DBUtil.getFbData(userid,"POST",date);
		List<FBPost> posts= processPostData(postsData);

		fbdata.setEvents(events);
		fbdata.setStatus(status);
		fbdata.setPhotos(photos);
		fbdata.setPosts(posts);	

		return fbdata;
	}

	private List<FBPost> processPostData(List<Facebook> fbData) throws ParseException, JSONException {
		// TODO Auto-generated method stub
		List<FBPost> list=new ArrayList<FBPost>();
		for (Facebook facebook : fbData) {
			JSONObject postJson=new JSONObject(facebook.getData());
			if(postJson.has("status_type")){
				String dateString=postJson.getString("created_time");
				SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
				Date date = incomingFormat.parse(dateString);
				if(postJson.getString("status_type").equalsIgnoreCase("approved_friend")){
					FBPost post=new FBPost();
					post.setMessage(postJson.getString("story"));
					//post.setPeople(postJson.getJSONObject("story_tags").getJSONArray("24").getJSONObject(0).getString("name"));
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
					String time = timeFormat.format(date);
					post.setTime(time);
					list.add(post);
				}
			}
		}
		return list;
	}

	private List<FBPhoto> processPhotoData(List<Facebook> fbData) throws ParseException {
		// TODO Auto-generated method stub
		List<FBPhoto> list=new ArrayList<FBPhoto>();
		try {
			for (Facebook facebook : fbData) {

				JSONObject photoJson=new JSONObject(facebook.getData());
				String dateString=photoJson.getString("created_time");
				SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
				Date date = incomingFormat.parse(dateString);

				FBPhoto photo=new FBPhoto();
				photo.setUrl(photoJson.getString("source"));
				if(photoJson.has("place")){
					photo.setLocation(photoJson.getJSONObject("place").getString("name"));
					JSONObject loc=photoJson.getJSONObject("place").getJSONObject("location");
					photo.setLat(loc.getDouble("latitude"));
					photo.setLon(loc.getDouble("longitude"));
				}
					
				ArrayList<String> tags=new ArrayList<>();
				if(photoJson.has("tags")){
					JSONArray tagArray=photoJson.getJSONObject("tags").getJSONArray("data");
					for (int i=0;i<tagArray.length();i++) {
						tags.add(tagArray.getJSONObject(i).getString("name"));
					}
				}
				photo.setPeople(tags);
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				String time = timeFormat.format(date);
				photo.setTime(time);
				list.add(photo);


			} 
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<FBStatus> processStatusData(List<Facebook> fbData) throws ParseException {
		// TODO Auto-generated method stub
		List<FBStatus> list=new ArrayList<FBStatus>();
		try {
			for (Facebook facebook : fbData) {

				JSONObject statusJson=new JSONObject(facebook.getData());
				String dateString=statusJson.getString("updated_time");
				SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
				Date date = incomingFormat.parse(dateString);
				FBStatus status=new FBStatus();
				status.setMessage(statusJson.getString("message"));
				status.setMood(SentimentClassifier.classify(status.getMessage()));
				status.setLocation(statusJson.getJSONObject("place").getString("name"));
				JSONObject loc=statusJson.getJSONObject("place").getJSONObject("location");
				status.setLat(loc.getDouble("latitude"));
				status.setLon(loc.getDouble("longitude"));
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				String time = timeFormat.format(date);
				status.setTime(time);
				list.add(status);


			} 
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<FBEvent> processEventData(List<Facebook> fbData) throws ParseException {
		// TODO Auto-generated method stub
		List<FBEvent> list=new ArrayList<FBEvent>();
		try {
			if(fbData!=null){
				for (Facebook facebook : fbData) {
					JSONObject eventJson=new JSONObject(facebook.getData());
					String dateString=eventJson.getString("start_time");
					SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
					Date date = incomingFormat.parse(dateString);
					if(eventJson.getString("rsvp_status").equalsIgnoreCase("attending")){
						FBEvent event=new FBEvent();
						event.setName(eventJson.getString("name"));
						event.setLocation(eventJson.getString("location"));
						JSONObject loc=eventJson.getJSONObject("venue");
						event.setLat(loc.getDouble("latitude"));
						event.setLon(loc.getDouble("longitude"));
						SimpleDateFormat outgoingFormat = new SimpleDateFormat("HH:mm:ss");
						String time = outgoingFormat.format(date);
						event.setTime(time);
						list.add(event);

					}
			}
			

			} 
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String GetLocalDateStringFromUTCString(String utcLongDateTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String localDateString = null;
		long when = 0;
		try {
			when = dateFormat.parse(utcLongDateTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		localDateString = dateFormat.format(new Date(when + TimeZone.getDefault().getRawOffset() + (TimeZone.getDefault().inDaylightTime(new Date()) ? TimeZone.getDefault().getDSTSavings() : 0)));

		return localDateString;
	}

}
