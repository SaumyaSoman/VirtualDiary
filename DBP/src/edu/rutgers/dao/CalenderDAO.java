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

import edu.rutgers.data.gcal.CalEvent;
import edu.rutgers.data.gcal.CalendarData;
import edu.rutgers.data.twitter.Tweet;
import edu.rutgers.data.twitter.TwitterData;
import edu.rutgers.model.GoogleCal;
import edu.rutgers.model.Twitter;
import edu.rutgers.util.DBUtil;
import edu.rutgers.util.SentimentClassifier;

public class CalenderDAO {

	String date;

	public static void main (String args[]) throws ParseException, JSONException{

		CalenderDAO dao=new CalenderDAO();
		String date="2011-11-08";
		System.out.println(dao.getData("534333f195959b1cb0cc52d4",date));
	}

	public CalendarData getData(String userId,String date) throws ParseException, JSONException {
		// TODO Auto-generated method stub
		this.date=date;
		CalendarData calData=new CalendarData();

		List<GoogleCal> calendarData=DBUtil.getGCalData(userId,"EVENT",date);
		List<CalEvent> events= processGCalData(calendarData);		

		calData.setEvents(events);

		return calData;
	}

	private List<CalEvent> processGCalData(List<GoogleCal> calendarData) throws ParseException {
		// TODO Auto-generated method stub
		List<CalEvent> list=new ArrayList<CalEvent>();
		try {
			for (GoogleCal cal : calendarData) {
				if(cal.getData() != null)
				{
					JSONObject eventJson=new JSONObject(cal.getData());
					JSONObject dateObject=eventJson.getJSONObject("start");
					if(dateObject.has("dateTime")){
						String dateString=dateObject.getString("dateTime");
						System.out.println(dateString);
						SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-HH:mm");
						Date date = incomingFormat.parse(dateString);
						SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
						String time = timeFormat.format(date);
						CalEvent event=new CalEvent();
						event.setMessage(eventJson.getString("summary"));						
						event.setTime(time.toString());			
						JSONArray tagArray=eventJson.getJSONArray("attendees");
						ArrayList<String> tags=new ArrayList<>();
						for (int i=0;i<tagArray.length();i++) {
							tags.add(tagArray.getJSONObject(i).getString("displayName"));
						}
						event.setTags(tags);
						list.add(event);

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
