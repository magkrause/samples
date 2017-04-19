package com.mycom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

public class LangDetectionServiceImplTest {
	
	@Spy
	private LangDetectionServiceImpl langDetectionServiceImpl;
	
	@Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        langDetectionServiceImpl.apiKey = "c498f124acfa668b876a3a5ae50315a7";
    }
	
	@Test
	public void testDetect_happyPath() throws APIError {
		List<Result> results = new ArrayList<Result>();
		Result r = new Result();
		r.language = "en";
		results.add(r);
		Mockito.when(langDetectionServiceImpl.performDetection(Mockito.anyString())).thenReturn(results);
		String lang = langDetectionServiceImpl.detect("test");
		Assert.assertEquals(r.language, lang);
	}
	
	@Test
	public void testDetect_nullResults() throws APIError {
		Mockito.when(langDetectionServiceImpl.performDetection(Mockito.anyString())).thenReturn(null);
		String lang = langDetectionServiceImpl.detect("test");
		Assert.assertEquals(null, lang);
	}
	
	@Test
	public void testDetect_emptyResults() throws APIError {
		Mockito.when(langDetectionServiceImpl.performDetection(Mockito.anyString())).thenReturn(new ArrayList<Result>());
		String lang = langDetectionServiceImpl.detect("test");
		Assert.assertEquals(null, lang);
	}
	
	@Test
	public void testDetect_APIError() throws APIError {
		Mockito.when(langDetectionServiceImpl.performDetection(Mockito.anyString())).thenThrow(new APIError("", 0));
		String lang = langDetectionServiceImpl.detect("test");
		Assert.assertEquals(null, lang);
	}
	
	@Test
	public void testDetect_Exception() throws APIError {
		Mockito.when(langDetectionServiceImpl.performDetection(Mockito.anyString())).thenThrow(new RuntimeException());
		String lang = langDetectionServiceImpl.detect("test");
		Assert.assertEquals(null, lang);
	}

}
