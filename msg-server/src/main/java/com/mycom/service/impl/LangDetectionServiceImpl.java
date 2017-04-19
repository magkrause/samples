package com.mycom.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.mycom.service.LangDetectionService;

public class LangDetectionServiceImpl implements LangDetectionService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LangDetectionServiceImpl.class);
	
	@Value("${langdetect.apikey:c498f124acfa668b876a3a5ae50315a7}")
	public String apiKey;
	
	@Override
	public String detect(String message) {
		List<Result> results;
		try {
			logger.info("Detecting language for message={}, using key={}.",message,DetectLanguage.apiKey);
			results = this.performDetection(message);
			if(results != null && !results.isEmpty()){
				logger.info("Detected {} languages for message={}, using key={}.",results.size(),message,DetectLanguage.apiKey);
				Result result = results.get(0);
				logger.info("Returning lang={}.",result.language);
				return result.language;
			}
			logger.info("Detected 0 languages for message={}, using key={}.",message,DetectLanguage.apiKey);
			return null;
		} catch (APIError e) {
			logger.error("Exception detecting language for message={}.",message,e);
		} catch (Exception e) {
			logger.error("Unexpected exception detecting language for message={}.",message,e);
		}
		return null;
	}
	
	protected List<Result> performDetection(String message) throws APIError{
		DetectLanguage.apiKey = this.apiKey;
		return DetectLanguage.detect(message);
	}

}
