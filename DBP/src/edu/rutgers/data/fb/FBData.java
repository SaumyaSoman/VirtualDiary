package edu.rutgers.data.fb;

import java.util.List;

public class FBData {
	
	List<FBEvent> events;
	/**
	 * @return the events
	 */
	public List<FBEvent> getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void setEvents(List<FBEvent> events) {
		this.events = events;
	}
	/**
	 * @return the status
	 */
	public List<FBStatus> getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(List<FBStatus> status) {
		this.status = status;
	}
	/**
	 * @return the photos
	 */
	public List<FBPhoto> getPhotos() {
		return photos;
	}
	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(List<FBPhoto> photos) {
		this.photos = photos;
	}
	/**
	 * @return the posts
	 */
	public List<FBPost> getPosts() {
		return posts;
	}
	/**
	 * @param posts the posts to set
	 */
	public void setPosts(List<FBPost> posts) {
		this.posts = posts;
	}
	List<FBStatus> status;
	List<FBPhoto> photos;
	List<FBPost> posts;
	@Override
	public String toString() {
		return "FBData [events=" + events + ", status=" + status + ", photos="
				+ photos + ", posts=" + posts + "]";
	}
	
	
	
	
}
