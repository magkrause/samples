package com.mycom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageInfo {
	
	private String language_code;
	private String language_name;
	private String probability;
	private String percentage;
	private String reliable_result;
	public String getLanguage_code() {
		return language_code;
	}
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}
	public String getLanguage_name() {
		return language_name;
	}
	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String perccentage) {
		this.percentage = perccentage;
	}
	public String getReliable_result() {
		return reliable_result;
	}
	public void setReliable_result(String reliable_result) {
		this.reliable_result = reliable_result;
	}
	
	
}
