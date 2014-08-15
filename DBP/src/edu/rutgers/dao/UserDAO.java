package edu.rutgers.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import edu.rutgers.data.fb.FBData;
import edu.rutgers.data.fb.FBEvent;
import edu.rutgers.data.fb.FBPhoto;
import edu.rutgers.data.fb.FBPost;
import edu.rutgers.data.fb.FBStatus;
import edu.rutgers.data.user.UserData;
import edu.rutgers.model.Facebook;
import edu.rutgers.model.User;
import edu.rutgers.util.DBUtil;

public class UserDAO {
	
	public UserData getUserData(String userName) throws ParseException, JSONException {
		// TODO Auto-generated method stub		
		UserData userData=new UserData();
		
		User user=DBUtil.getUserData(userName);
		userData= processUserData(user);
		return userData;
	}
	
	private UserData processUserData(User user) throws ParseException {
		// TODO Auto-generated method stub
		UserData userData = null;
		try {
					String id = user.get_id();
					String userName = user.getUsername();
					userData = new UserData();
					userData.setUserId(id);
					userData.setUserName(userName);
					}
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return userData;
	}
}
