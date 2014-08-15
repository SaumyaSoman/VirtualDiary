package edu.rutgers.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;


import edu.rutgers.data.fsq.FSQData;
import edu.rutgers.data.fsq.FSQEvent;
import edu.rutgers.model.Foursquare;
import edu.rutgers.util.Constants;
import edu.rutgers.util.DBUtil;
import edu.rutgers.util.SentimentClassifier;

public class FoursquareDAO {

	String date;
	
	public static void main (String args[]) throws ParseException, JSONException{

		FoursquareDAO dao=new FoursquareDAO();
		String date="2014-04-19";
		System.out.println(dao.getData("5343633d87677b24604dada5",date));
	}
	
	public FSQData getData(String userid, String date) throws ParseException, JSONException {
		// TODO Auto-generated method stub
		this.date=date;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date date1 = df.parse(date);
	    long epoch1 = date1.getTime();
	    Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.add(Calendar.DATE, 1); 
		long epoch2 = c.getTime().getTime();
		System.out.println(epoch1);
		System.out.println(epoch2);
		FSQData fsqdata=new FSQData();
		List<FSQEvent> events=new ArrayList<>();
		List<Foursquare> checkinData=DBUtil.getFSQData(userid,"CHECKIN",epoch1/1000,epoch2/1000);
		events=processEventData(events,checkinData);		
		fsqdata.setEvents(events);	
		return fsqdata;
	}

	
	private List<FSQEvent> processEventData(List<FSQEvent> events, List<Foursquare> fsqData) throws ParseException {
		// TODO Auto-generated method stub
		List<FSQEvent> list=new ArrayList<FSQEvent>();
		try {
			for (Foursquare fsq : fsqData) {
					JSONObject eventJson=new JSONObject(fsq.getData());
					long dateEpoch=eventJson.getLong("createdAt");
					Date date = new Date(dateEpoch*1000);
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String date1= formatter.format(date);
					if(date1.equals(this.date)){
						FSQEvent event=new FSQEvent();
						if(eventJson.has("shout")){
							event.setMessage(eventJson.getString("shout"));
							event.setMood(SentimentClassifier.classify(event.getMessage()));
						}				
						
						event.setLocation(eventJson.getJSONObject("venue").getString("name"));
						JSONObject loc=eventJson.getJSONObject("venue").getJSONObject("location");
						event.setLat(loc.getDouble("lat"));
						event.setLon(loc.getDouble("lng"));
						if(eventJson.has("with")){
							ArrayList<String> tags=new ArrayList<>();
							JSONArray tagArray=eventJson.getJSONArray("with");
							for (int i=0;i<tagArray.length();i++) {
								tags.add(tagArray.getJSONObject(i).getString("firstName")+" "+tagArray.getJSONObject(i).getString("lastName"));
							}
							event.setTags(tags);
						}
						DateFormat formatter1 = new SimpleDateFormat("hh:MM:ss");
						String time= formatter1.format(date);
						event.setTime(time);
						list.add(event);
						
					}
					
				} 
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}

}

