package edu.rutgers.model;

import com.google.code.morphia.annotations.Entity;
@Entity("user")
public class User {
	
	private String _id;
	private String username;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
