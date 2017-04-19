package com.mycom.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageResult {
	
	private Boolean success;
	List<LanguageInfo> results;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<LanguageInfo> getResults() {
		return results;
	}
	public void setResults(List<LanguageInfo> results) {
		this.results = results;
	}
	
}
