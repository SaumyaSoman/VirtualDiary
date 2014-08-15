package edu.rutgers.model;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;


@Entity("facebook_data")
public class Facebook {
	
	private String data_type;
	private String data;
	private ObjectId neemiuser;
	
	
	
	public ObjectId getNeemiuser() {
		return neemiuser;
	}
	public void setNeemiuser(ObjectId neemiuser) {
		this.neemiuser = neemiuser;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Facebook [data_type=" + data_type + ", data=" + data
				+ ", getData_type()=" + getData_type() + ", getData()="
				+ getData() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
