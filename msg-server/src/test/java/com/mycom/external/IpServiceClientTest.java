package com.mycom.external;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.model.GeoLocationInfo;

public class IpServiceClientTest {
	
	@Mock
	private ObjectMapper mapper;
	
	@InjectMocks
	private IpServiceClient ipServiceClient;
	
	@Before
    public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		ipServiceClient.url = "http://freegeoip.net/json/";
    }
    
	@Test
	public void testGetGeoLocation_happyPath() throws JsonParseException, JsonMappingException, IOException {
		GeoLocationInfo info = new GeoLocationInfo();
		info.setCountry_name("United States");
		
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenReturn(info);
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(info.getCountry_name(),result.getCountry_name());
	}  
	
	@Test
	public void testGetGeoLocation_jsonParseException() throws JsonParseException, JsonMappingException, IOException {
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenThrow(new JsonParseException(null, "test"));
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(null,result);
	}  
	
	@Test
	public void testGetGeoLocation_jsonMappingException() throws JsonParseException, JsonMappingException, IOException {
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenThrow(new JsonMappingException(null, "test"));
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(null,result);
	}  
	
	@Test
	public void testGetGeoLocation_ioException() throws JsonParseException, JsonMappingException, IOException {
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenThrow(new IOException());
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(null,result);
	} 
	
	@Test
	public void testGetGeoLocation_malformedURLException() throws JsonParseException, JsonMappingException, IOException {
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenThrow(new MalformedURLException());
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(null,result);
	} 
	
	@Test
	public void testGetGeoLocation_runtimeException() throws JsonParseException, JsonMappingException, IOException {
		Mockito.when(mapper.readValue(Mockito.any(URL.class), Matchers.<Class<GeoLocationInfo>>any())).thenThrow(new RuntimeException());
		
		GeoLocationInfo result = ipServiceClient.getGeoLocationInfo("www.mycom.com");
		
		Assert.assertEquals(null,result);
	} 
}
