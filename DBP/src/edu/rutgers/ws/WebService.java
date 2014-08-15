package edu.rutgers.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import edu.rutgers.dao.CalenderDAO;
import edu.rutgers.dao.FacebookDAO;
import edu.rutgers.dao.FoursquareDAO;
import edu.rutgers.dao.TwitterDAO;
import edu.rutgers.dao.UserDAO;
import edu.rutgers.data.fb.FBData;
import edu.rutgers.data.fb.FBEvent;
import edu.rutgers.data.fb.FBPhoto;
import edu.rutgers.data.fb.FBPost;
import edu.rutgers.data.fb.FBStatus;
import edu.rutgers.data.fsq.FSQData;
import edu.rutgers.data.fsq.FSQEvent;
import edu.rutgers.data.gcal.CalEvent;
import edu.rutgers.data.gcal.CalendarData;
import edu.rutgers.data.twitter.Tweet;
import edu.rutgers.data.twitter.TwitterData;
import edu.rutgers.data.user.UserData;
import edu.rutgers.model.GoogleCal;
import edu.rutgers.model.Mood;
import edu.rutgers.util.Constants;

@Path("/ws")
public class WebService{


	@GET
	@Path("/loginService")
	//@Produces(MediaType.Str)
	public String login(@QueryParam("userName") String userName) {

		System.out.println("In login service");

		UserDAO userDAO=new UserDAO();
		UserData userData = null;
		try {
			userData=userDAO.getUserData(userName);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userData.getUserId();
	}



	@GET
	@Path("/getData")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse getData(@QueryParam("date") String date,@QueryParam("userId") String userid ) {

		System.out.println("aa");
		FacebookDAO fb=new FacebookDAO();
		TwitterDAO tw=new TwitterDAO();
		FoursquareDAO fsq=new FoursquareDAO();
		CalenderDAO cal=new CalenderDAO();
		FBData fbData = null;
		TwitterData twData=null;
		FSQData fsqData=null;
		CalendarData calData=null;
		try {
			fbData=fb.getData(date,userid);
			twData=tw.getData(userid, date);
			fsqData=fsq.getData(userid, date);
			calData=cal.getData(userid, date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		WSResponse response=new WSResponse();
		response.setDate(date.toString());
		ArrayList<Events> events=new ArrayList<>();
		response.setEvents(events);
		response=addFBData(response,fbData);
		response=addTwitterData(response,twData);
		response=addFSQData(response,fsqData);
		response=addCalData(response,calData);
		if(response.getScore()>0){
			response.setMood(Constants.POS);
		}
		if(response.getScore()<0){
			response.setMood(Constants.NEG);
		}	
		
	    return response;
	}

	@GET
	@Path("/getMood")
	@Produces(MediaType.APPLICATION_JSON)
	public MoodResponse getMood(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate,@QueryParam("userId") String userid ) {

		MoodResponse resp=new MoodResponse();
		if(startDate==null) return resp;
		if(endDate==null){
			endDate=Calendar.getInstance().toString();
		}
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date sDate= formatter.parse(startDate);
			Date eDate= formatter.parse(endDate);
			
			ArrayList<Double> scores=new ArrayList<>();
			int facebook=0, twitter=0, foursquare=0, gcal=0;
			while(sDate.before(eDate) || sDate.equals(eDate)){
				
				TwitterDAO tw=new TwitterDAO();
				FoursquareDAO fsq=new FoursquareDAO();
				CalenderDAO cal=new CalenderDAO();
				FacebookDAO fb=new FacebookDAO();
				FBData fbData = null;
				TwitterData twData=null;
				FSQData fsqData=null;
				CalendarData calData=null;
				System.out.println("before get data for facebook..." + sDate);
				fbData=fb.getData(formatter.format(sDate),userid);
				twData=tw.getData(userid, formatter.format(sDate));
				fsqData=fsq.getData(userid, formatter.format(sDate));
				calData=cal.getData(userid, formatter.format(sDate));


				WSResponse response=new WSResponse();
				response.setDate(formatter.format(sDate));
				ArrayList<Events> events=new ArrayList<>();
				response.setEvents(events);
				response=addFBData(response,fbData);
				response=addTwitterData(response,twData);
				response=addFSQData(response,fsqData);
				response=addCalData(response,calData);
				
				facebook=facebook+response.getFacebook();
				foursquare+=response.getFoursquare();
				gcal+=response.getGcal();
				twitter+=response.getTwitter();
				
				if(response.getScore()>0){
					resp.setHappy(resp.getHappy()+1);
				}if(response.getScore()<0){
					resp.setSad(resp.getSad()+1);
				}else{
					resp.setMixed(resp.getMixed()+1);
				}
				
				scores.add(response.getScore());
				
				
				
				
				Calendar c = Calendar.getInstance();
				c.setTime(sDate);
				c.add(Calendar.DATE, 1);  // number of days to add
				startDate = formatter.format(c.getTime());
				sDate=formatter.parse(startDate);
			}
			resp.setScores(scores);
			resp.setFacebook(facebook);
			resp.setTwitter(twitter);
			resp.setGcal(gcal);
			resp.setFoursquare(foursquare);
		}catch (ParseException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return resp;
	}
	private WSResponse addCalData(WSResponse response, CalendarData calData) {
		if(calData!=null){
			if(calData.getEvents()!=null && calData.getEvents().size()>0){
				for (CalEvent cal : calData.getEvents()) {
					Events event=new Events();
					event.setMessage(cal.getMessage());
					event.setSource("googlecalendar");
					event.setTime(cal.getTime());
					if(cal.getTags()!=null && cal.getTags().size()>0){
						Tags tag=new Tags();
						ArrayList<String> tags=new ArrayList<>();
						for (String string : cal.getTags()) {
							tags.add(string);						
						}
						tag.setName(tags);
						event.setTags(tag);
					}	
					response.setGcal(response.getGcal()+1);
					response.getEvents().add(event);
				}

			}
		}
		return response;
	}



	private WSResponse addFSQData(WSResponse response, FSQData fsqData) {
		if(fsqData!=null){
			if(fsqData.getEvents()!=null && fsqData.getEvents().size()>0){
				for (FSQEvent fsq : fsqData.getEvents()) {
					Events event=new Events();
					event.setMessage(fsq.getMessage());
					event.setMood(fsq.getMood().getType());
					event.setSource("foursquare");
					event.setTime(fsq.getTime());
					event.setLocation(fsq.getLocation());
					event.setLat(fsq.getLat());
					event.setLon(fsq.getLon());
					if(fsq.getTags()!=null && fsq.getTags().size()>0){
						Tags tag=new Tags();
						ArrayList<String> tags=new ArrayList<>();
						for (String string : fsq.getTags()) {
							tags.add(string);						
						}
						tag.setName(tags);
						event.setTags(tag);
					}	
					response.getEvents().add(event);
					response.setFoursquare(response.getFoursquare()+1);
					response.setScore(response.getScore()+fsq.getMood().getScore());
				}

			}
		}
		return response;
	}



	private WSResponse addTwitterData(WSResponse response, TwitterData twData) {
		// TODO Auto-generated method stub
		if(twData!=null){
			if(twData.getTweets()!=null && twData.getTweets().size()>0){
				for (Tweet tweet : twData.getTweets()) {
					Events event=new Events();
					event.setMessage(tweet.getMessage());
					event.setMood(tweet.getMood().getType());
					event.setSource("twitter");
					event.setTime(tweet.getTime());
					response.getEvents().add(event);
					response.setTwitter(response.getTwitter()+1);
					response.setScore(response.getScore()+tweet.getMood().getScore());
				}
			}
		}
		return response;
	}



	private WSResponse addFBData(WSResponse response, FBData fbData) {
		// TODO Auto-generated method stub
		if(fbData==null) return response;

		if(fbData.getEvents()!=null && fbData.getEvents().size()>0){
			for (FBEvent eventData : fbData.getEvents()) {
				Events event=new Events();
				event.setMessage(eventData.getName());
				event.setRsvp_status("attending");
				event.setMood("happy");
				event.setSource("facebook");
				event.setTime(eventData.getTime());
				event.setLocation(eventData.getLocation());
				event.setLat(eventData.getLat());
				event.setLon(eventData.getLon());
				response.getEvents().add(event);
				response.setScore(response.getScore()+0.5);
				response.setFacebook(response.getFacebook()+1);
			}
		}
		if(fbData.getPhotos()!=null && fbData.getPhotos().size()>0){
			for (FBPhoto photoData : fbData.getPhotos()) {
				Events photo=new Events();
				photo.setPic_url(photoData.getUrl());
				photo.setMood(Constants.NEU);
				photo.setSource("facebook");
				photo.setTime(photoData.getTime());
				photo.setLocation(photoData.getLocation());
				photo.setLat(photoData.getLat());
				photo.setLon(photoData.getLon());
				if(photoData.getPeople()!=null && photoData.getPeople().size()>0){
					Tags tag=new Tags();
					ArrayList<String> tags=new ArrayList<>();
					for (String string : photoData.getPeople()) {
						tags.add(string);						
					}
					tag.setName(tags);
					photo.setTags(tag);
				}
				response.setFacebook(response.getFacebook()+1);
				response.getEvents().add(photo);
			}
		}

		if(fbData.getPosts()!=null && fbData.getPosts().size()>0){
			for (FBPost statusData : fbData.getPosts()) {
				Events status=new Events();
				status.setMessage(statusData.getMessage());
				status.setMood(Constants.NEU);
				status.setSource("facebook");
				status.setTime(statusData.getTime());
				Tags tag=new Tags();
				ArrayList<String> tags=new ArrayList<String>();
				tags.add(statusData.getPeople());
				status.setTags(tag);
				response.setFacebook(response.getFacebook()+1);
				response.getEvents().add(status);
			}
		}

		if(fbData.getStatus()!=null && fbData.getStatus().size()>0){
			for (FBStatus statusData : fbData.getStatus()) {
				Events status=new Events();				
				status.setMessage(statusData.getMessage());
				status.setMood(statusData.getMood().getType());
				status.setSource("facebook");
				status.setLocation(statusData.getLocation());
				status.setLat(statusData.getLat());
				status.setLon(statusData.getLon());
				status.setTime(statusData.getTime());
				if(statusData.getPeople()!=null && statusData.getPeople().size()>0){
					Tags tag=new Tags();
					ArrayList<String> tags=new ArrayList<>();
					for (String string : statusData.getPeople()) {
						tags.add(string);						
					}
					tag.setName(tags);
					status.setTags(tag);
				}				
				response.getEvents().add(status);
				response.setFacebook(response.getFacebook()+1);
				response.setScore(response.getScore()+statusData.getMood().getScore());

			}

		}
		return response;
	}
}
