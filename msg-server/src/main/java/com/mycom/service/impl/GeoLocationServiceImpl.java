package com.mycom.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mycom.external.IpServiceClient;
import com.mycom.model.GeoLocationInfo;
import com.mycom.service.GeoLocationService;

public class GeoLocationServiceImpl implements GeoLocationService{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GeoLocationServiceImpl.class);
	
	@Autowired
	private IpServiceClient ipServiceClient;

	@Override
	public String getCountry(String ip) {
		logger.info("Looking for country for ip={}",ip);
		String result = "unknown location";
		
		if(StringUtils.isEmpty(ip)){
			logger.info("Ip={} is empty. Ending flow.",ip);
			return result;
		}
		
		GeoLocationInfo info = ipServiceClient.getGeoLocationInfo(ip);
		if(info != null && !StringUtils.isEmpty(info.getCountry_name()))
			result = info.getCountry_name();
		
		logger.info("Returning country={}",result);
		return result;
	}
}
