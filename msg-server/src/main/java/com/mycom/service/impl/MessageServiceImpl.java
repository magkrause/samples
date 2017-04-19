package com.mycom.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycom.dao.MessageDAO;
import com.mycom.model.Message;
import com.mycom.service.LangDetectionService;
import com.mycom.service.MessageService;

public class MessageServiceImpl implements MessageService{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private LangDetectionService langDetectionService;
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public List<Message> list(Integer numOf, String lang){
		logger.info("Listing messages for numOf={} lang={}.",numOf,lang);
		return messageDAO.list(numOf,lang);
	}

	@Override
	public Message submit(String input,String country) {
		logger.info("Detecting language for message={} country={}.",input,country);
		String lang = langDetectionService.detect(input);
		
		Message m = new Message();
		m.setLang(lang);
		m.setCountry(country);
		m.setInput(input);
		
		logger.info("Saving message={}.",m.getMessage());
		messageDAO.save(m);
		logger.info("Message={} successfully saved.",m.getMessage());
		return m;
	}

}
