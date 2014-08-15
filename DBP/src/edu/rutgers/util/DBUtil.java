package edu.rutgers.util;

import java.util.List;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;


import edu.rutgers.model.Facebook;
import edu.rutgers.model.Foursquare;
import edu.rutgers.model.GoogleCal;
import edu.rutgers.model.Twitter;
import edu.rutgers.model.User;

public class DBUtil {

	public static List<Facebook> getFbData(String userId,String datatype, String date){
		List<Facebook> dataList=null;
		try {
			Datastore datastore = MorphiaUtil.getDatastore();
			ObjectId id=new ObjectId(userId);
			Query<Facebook> query= datastore.createQuery(Facebook.class).disableValidation().filter("neemiuser", id).
					filter("data_type", datatype);
			query.or(
					query.criteria("data.updated_time").startsWith(date),
					query.criteria("data.created_time").startsWith(date),
					query.criteria("data.start_time").startsWith(date)
				);
			dataList=query.asList();	
			System.out.println(dataList.size());
			for (Facebook facebook : dataList) {
				System.out.println(facebook);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dataList;
	}
	
	public static User getUserData(String userName){		
		User user = null;
		try {
			Datastore datastore = MorphiaUtil.getDatastore();
			//Query<DataDB> query= datastore.find(DataDB.class, "searchId", searchId);
			Query<User> query= datastore.find(User.class,"username",userName);
			user=query.get();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static List<Twitter> getTwitterData(String userId,String datatype,String date){
		List<Twitter> dataList=null;
		try {
			Datastore datastore = MorphiaUtil.getDatastore();
			ObjectId id=new ObjectId(userId);
			Pattern regex=Pattern.compile(date);
			Query<Twitter> query= datastore.createQuery(Twitter.class).disableValidation().filter("neemi_user", id).filter("data_type", datatype)
					.filter("data.created_at", regex);
			dataList=query.asList();	
			System.out.println(dataList.size());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dataList;
	}

	public static List<Foursquare> getFSQData(String userId, String datatype, long epoch1,long epoch2) {
		// TODO Auto-generated method stub
		List<Foursquare> dataList=null;
		try {
			Datastore datastore = MorphiaUtil.getDatastore();
			ObjectId id=new ObjectId(userId);
			Query<Foursquare> query= datastore.createQuery(Foursquare.class).disableValidation().filter("neemi_user", id).filter("data_type", datatype);
			query.field("data.createdAt").greaterThan(epoch1);
			query.field("data.createdAt").lessThan(epoch2);
			dataList=query.asList();
			System.out.println(dataList.size());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dataList;
	}
	
	public static List<GoogleCal> getGCalData(String userId, String datatype,String date) {
		// TODO Auto-generated method stub
		List<GoogleCal> dataList=null;
		try {
			Datastore datastore = MorphiaUtil.getDatastore();
			ObjectId id=new ObjectId(userId);
			Pattern regex=Pattern.compile(date);
			Query<GoogleCal> query= datastore.createQuery(GoogleCal.class).disableValidation().filter("neemi_user", id).
					filter("data_type", datatype).filter("data.start.dateTime", regex);
			dataList=query.asList();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dataList;
	}

}
