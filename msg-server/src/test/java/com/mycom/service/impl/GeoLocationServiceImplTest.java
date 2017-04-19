package com.mycom.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mycom.external.IpServiceClient;
import com.mycom.model.GeoLocationInfo;

public class GeoLocationServiceImplTest {

	@Mock
	private IpServiceClient ipServiceClient;
	
	@InjectMocks
	private GeoLocationServiceImpl geoLocationServiceImpl;
	
	@Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGetCountry_happyPath() {
		GeoLocationInfo info = new GeoLocationInfo();
		info.setCountry_name("United States");
		Mockito.when(ipServiceClient.getGeoLocationInfo(Mockito.anyString())).thenReturn(info);
		String result = geoLocationServiceImpl.getCountry("test");
		Assert.assertEquals("United States", result);
	}
	
	@Test
	public void testGetCountry_nullIp() {
		String result = geoLocationServiceImpl.getCountry(null);
		Assert.assertEquals("unknown location", result);
	}
	
	@Test
	public void testGetCountry_nullInfo() {
		Mockito.when(ipServiceClient.getGeoLocationInfo(Mockito.anyString())).thenReturn(null);
		String result = geoLocationServiceImpl.getCountry("test");
		Assert.assertEquals("unknown location", result);
	}
	
	@Test
	public void testGetCountry_nullCountryName() {
		GeoLocationInfo info = new GeoLocationInfo();
		info.setCountry_name(null);
		Mockito.when(ipServiceClient.getGeoLocationInfo(Mockito.anyString())).thenReturn(info);
		String result = geoLocationServiceImpl.getCountry("test");
		Assert.assertEquals("unknown location", result);
	}
	
}
