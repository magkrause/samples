package com.mycom.model;

import java.util.Date;

public class Message {
	
	private static int seq = 0;
	
	private int id;
	private Date creationDate;
	private String input;
	private String country;
	private String lang;
	
	public Message(){
		this.id = seq;
		incrementSeq();
		this.creationDate =  new Date();
	}
	
	public static synchronized void incrementSeq(){
		seq++;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCreationDate(){
		return this.creationDate.toString();
	}
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append(input);
		sb.append(" from ");
		sb.append(country);
		sb.append("!");
		return sb.toString();
	}
	
	public String getLang() {
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Message)obj).getId();
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
}
