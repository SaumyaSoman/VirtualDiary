package edu.rutgers.model;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;

@Entity("foursquare_data")
public class Foursquare {

	private String data_type;
	private String data;
	private ObjectId neemi_user;
	
	
	public ObjectId getNeemiuser() {
		return neemi_user;
	}
	public void setNeemiuser(ObjectId neemiuser) {
		this.neemi_user = neemiuser;
	}
	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}
	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	
}
