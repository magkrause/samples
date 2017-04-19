package com.mycom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.mycom.dao.MessageDAO;
import com.mycom.dao.impl.MessageDAOImpl;
import com.mycom.external.IpServiceClient;
import com.mycom.service.GeoLocationService;
import com.mycom.service.LangDetectionService;
import com.mycom.service.MessageService;
import com.mycom.service.impl.GeoLocationServiceImpl;
import com.mycom.service.impl.LangDetectionServiceImpl;
import com.mycom.service.impl.MessageServiceImpl;

@Configuration
@PropertySource(value="classpath:config.properties",ignoreResourceNotFound=true)
public class MessageConfig {
	
	@Bean
	public MessageService messageService() {
		return new MessageServiceImpl();
	}
	
	@Bean
	public GeoLocationService geoLocationService(){
		return new GeoLocationServiceImpl();
	}

	@Bean
	public IpServiceClient ipServiceClient(){
		return new IpServiceClient();
	}
	
	@Bean
	public LangDetectionService langDetectionService(){
		return new LangDetectionServiceImpl();
	}
	
	@Bean
	public MessageDAO messageDAO(){
		return new MessageDAOImpl();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
