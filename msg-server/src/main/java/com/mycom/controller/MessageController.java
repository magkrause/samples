package com.mycom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.model.Message;
import com.mycom.model.Response;
import com.mycom.service.GeoLocationService;
import com.mycom.service.MessageService;
import com.mycom.utils.LangCode;

@RestController
@RequestMapping(value = "/v1/message")
public class MessageController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private GeoLocationService geoLocationService;
	
	@RequestMapping(value = "/submit/{input}", method = RequestMethod.POST)
	public Response<Message> submitMessage(HttpServletRequest request, @PathVariable("input") String input) {
		
		if(input.matches("\\d+")){
			logger.info("Invalid input input={}.", input);
			return new Response<Message>(HttpServletResponse.SC_BAD_REQUEST, "Input should only have chars.");
		}
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddr();
		}
		
		String country = geoLocationService.getCountry(ipAddress);
		try{
			Message message = messageService.submit(input, country);
			logger.info("Successfully saved message for input={}, country={}", input, country);
			return new Response<Message>(HttpServletResponse.SC_OK, message);
		}catch (Exception e){
			logger.error("Unexpected exception trying to submit message for input={}, country={}", input, country, e);
			return new Response<Message>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Exception submitting message.");
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response<List<Message>> listMessages(@RequestParam("numOf")String numOfStr, @RequestParam("lang")String lang) {
		Integer numOf = null;
		try{
			numOf = Integer.parseInt(numOfStr);	
		}catch(Exception e){
			logger.info("Unable to parse numOfStr={}.", numOfStr);
			return new Response<List<Message>>(HttpServletResponse.SC_BAD_REQUEST, "Invalid numOf. NumOf should be an integer.");
		}
		
		if(numOf<=0){
			logger.info("Invalid input numOf={}.", numOf);
			return new Response<List<Message>>(HttpServletResponse.SC_BAD_REQUEST, "Invalid numOf. NumOf should be > 0.");
		}
		
		if(!LangCode.validCode(lang)){
			logger.info("Invalid input lang={}.", lang);
			return new Response<List<Message>>(HttpServletResponse.SC_BAD_REQUEST, "Invalid language code.");
		}
		
		try{
			List<Message> messages = messageService.list(numOf, lang);
			logger.info("Successfully listed messages for numOf={}, lang={}", numOf, lang);
			return new Response<List<Message>>(HttpServletResponse.SC_OK, messages);
		}catch(Exception e){
			logger.error("Unexpected exception trying to list messages with params numOf={}, lang={}", numOf, lang, e);
			return new Response<List<Message>>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Exception listing messages.");
		}
	}
	
}
