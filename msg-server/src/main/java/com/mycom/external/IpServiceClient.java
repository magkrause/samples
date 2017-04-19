package com.mycom.external;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.model.GeoLocationInfo;

public class IpServiceClient {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(IpServiceClient.class);

	private ObjectMapper mapper = new ObjectMapper();
	
	@Value("${geoloc.url:http://freegeoip.net/json/}")
	public String url;
	
	public GeoLocationInfo getGeoLocationInfo(String ip){
		GeoLocationInfo obj;
		try {
			obj = mapper.readValue(new URL(url+ip), GeoLocationInfo.class);
			return obj;
		} catch (JsonParseException e) {
			logger.error("Exception parsing object returned from freegeoip for ip={}",ip,e);
		} catch (JsonMappingException e) {
			logger.error("Exception mapping object returned from freegeoip for ip={}",ip,e);
		} catch (MalformedURLException e) {
			logger.error("Exception calling freegeoip for ip={}",ip,e);
		} catch (IOException e) {
			logger.error("Exception calling freegeoip for ip={}",ip,e);
		} catch (Exception e){
			logger.error("Unexpected exception calling freegeoip for ip={}",ip,e);
		}
		return null;
	}
}
