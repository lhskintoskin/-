package com.jsu.wwt.vo;

public class Comment {
	private String comcontent;
	private String username;
	private String topic;
	private String data;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getComcontent() {
		return comcontent;
	}
	public void setComcontent(String comcontent) {
		this.comcontent = comcontent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Override
	public String toString() {
		return "Comment [comcontent=" + comcontent + ", username=" + username + ", topic=" + topic + ", data=" + data
				+ "]";
	}
	public Comment(String comcontent, String username, String topic, String data) {
		super();
		this.comcontent = comcontent;
		this.username = username;
		this.topic = topic;
		this.data = data;
	}
}