package com.jsu.wwt.vo;


public class Note {
	private String postcontent;
	private String topic;
	private String username;
	private int mylike;
	public int getLike() {
		return mylike;
	}
	public void setLike(int like) {
		this.mylike = mylike;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Note(String postcontent, String topic, String username, int like) {
		super();
		this.postcontent = postcontent;
		this.topic = topic;
		this.username = username;
		this.mylike = mylike;
	}
	@Override
	public String toString() {
		return "Note [postcontent=" + postcontent + ", topic=" + topic + ", username=" + username + ", mylike=" + mylike
				+ "]";
	}
	
	
	

}
