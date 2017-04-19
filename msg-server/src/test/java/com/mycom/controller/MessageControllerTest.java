package com.mycom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mycom.model.Message;
import com.mycom.model.Response;
import com.mycom.service.GeoLocationService;
import com.mycom.service.MessageService;

public class MessageControllerTest {
	
	@Mock
	private MessageService messageService;
	
	@Mock
	private GeoLocationService geoLocationService;
	
	@Mock
	private HttpServletRequest request;
	
	@InjectMocks
	private MessageController messageController;
	
	@Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSubmitMessage_happyPath() {
		String input = "Hello world";
		String country = "Argentina";
		
		Mockito.when(request.getHeader(Mockito.anyString())).thenReturn("www.mycom.com");
		Mockito.when(geoLocationService.getCountry(Mockito.anyString())).thenReturn(country);
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		Mockito.when(messageService.submit(Mockito.anyString(), Mockito.anyString())).thenReturn(message);

		Response<Message> response = messageController.submitMessage(request, input);
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getCode());
		Assert.assertEquals("Hello world from Argentina!", response.getResponse().getMessage());
	}  
	
	@Test
	public void testSubmitMessage_specialChars() {
		String input = "!@#$%^&*()'/;:?";
		String country = "Argentina";
		
		Mockito.when(request.getHeader(Mockito.anyString())).thenReturn("www.mycom.com");
		Mockito.when(geoLocationService.getCountry(Mockito.anyString())).thenReturn(country);
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		Mockito.when(messageService.submit(Mockito.anyString(), Mockito.anyString())).thenReturn(message);

		Response<Message> response = messageController.submitMessage(request, input);
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getCode());
		Assert.assertEquals("!@#$%^&*()'/;:? from Argentina!", response.getResponse().getMessage());
	}  

	
	@Test
	public void testSubmitMessage_invalidInput() {
		String input = "123456";
		String country = "Argentina";
		
		Mockito.when(request.getHeader(Mockito.anyString())).thenReturn("www.mycom.com");
		Mockito.when(geoLocationService.getCountry(Mockito.anyString())).thenReturn(country);
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		Mockito.when(messageService.submit(Mockito.anyString(), Mockito.anyString())).thenReturn(message);

		Response<Message> response = messageController.submitMessage(request, input);
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getCode());
		Assert.assertEquals("Input should only have chars.", response.getErrorMessage());
	}
	
	@Test
	public void testSubmitMessage_ipHeaderNull() {
		String input = "Hello world";
		String country = "Argentina";
		
		Mockito.when(request.getHeader(Mockito.anyString())).thenReturn(null);
		Mockito.when(request.getRemoteAddr()).thenReturn("www.mycom.com");
		Mockito.when(geoLocationService.getCountry(Mockito.anyString())).thenReturn(country);
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		Mockito.when(messageService.submit(Mockito.anyString(), Mockito.anyString())).thenReturn(message);

		Response<Message> response = messageController.submitMessage(request, input);
		
		Mockito.verify(request, Mockito.times(1)).getRemoteAddr();
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getCode());
		Assert.assertEquals("Hello world from Argentina!", response.getResponse().getMessage());
	}
	
	@Test
	public void testSubmitMessage_saveError() {
		String input = "Hello world";
		String country = "Argentina";
		
		Mockito.when(request.getHeader(Mockito.anyString())).thenReturn(null);
		Mockito.when(request.getRemoteAddr()).thenReturn("www.mycom.com");
		Mockito.when(geoLocationService.getCountry(Mockito.anyString())).thenReturn(country);
		Mockito.when(messageService.submit(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException());

		Response<Message> response = messageController.submitMessage(request, input);
		
		Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getCode());
		Assert.assertEquals("Exception submitting message.", response.getErrorMessage());
	}
	
	@Test
	public void testListMessages_happyPath() {
		String numOfStr = "1";
		String lang = "en";
		String input = "Hello world";
		String country = "Argentina";
		
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		List<Message> list = new ArrayList<Message>();
		list.add(message);
		Mockito.when(messageService.list(Mockito.anyInt(), Mockito.anyString())).thenReturn(list);

		Response<List<Message>> response = messageController.listMessages(numOfStr, lang);
		
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getCode());
		Assert.assertEquals("Hello world from Argentina!", response.getResponse().get(0).getMessage());
	}	
	
	@Test
	public void testListMessages_invalidNumOf() {
		String numOfStr = "e";
		String lang = "en";
		String input = "Hello world";
		String country = "Argentina";
		
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		List<Message> list = new ArrayList<Message>();
		list.add(message);
		Mockito.when(messageService.list(Mockito.anyInt(), Mockito.anyString())).thenReturn(list);

		Response<List<Message>> response = messageController.listMessages(numOfStr, lang);
		
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getCode());
		Assert.assertEquals("Invalid numOf. NumOf should be an integer.", response.getErrorMessage());
	}	
	
	@Test
	public void testListMessages_negativeNumOf() {
		String numOfStr = "-1";
		String lang = "en";
		String input = "Hello world";
		String country = "Argentina";
		
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		List<Message> list = new ArrayList<Message>();
		list.add(message);
		Mockito.when(messageService.list(Mockito.anyInt(), Mockito.anyString())).thenReturn(list);

		Response<List<Message>> response = messageController.listMessages(numOfStr, lang);
		
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getCode());
		Assert.assertEquals("Invalid numOf. NumOf should be > 0.", response.getErrorMessage());
	}	
	
	@Test
	public void testListMessages_invalidLang() {
		String numOfStr = "1";
		String lang = "mx";
		String input = "Hello world";
		String country = "Argentina";
		
		Message message = new Message();
		message.setCountry(country);
		message.setInput(input);
		List<Message> list = new ArrayList<Message>();
		list.add(message);
		Mockito.when(messageService.list(Mockito.anyInt(), Mockito.anyString())).thenReturn(list);

		Response<List<Message>> response = messageController.listMessages(numOfStr, lang);
		
		Assert.assertEquals(HttpServletResponse.SC_BAD_REQUEST, response.getCode());
		Assert.assertEquals(response.getErrorMessage(), "Invalid language code.");
	}	
	
	@Test
	public void testListMessages_listError() {
		String numOfStr = "1";
		String lang = "en";
		Mockito.when(messageService.list(Mockito.anyInt(), Mockito.anyString())).thenThrow(new RuntimeException());

		Response<List<Message>> response = messageController.listMessages(numOfStr, lang);
		
		Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getCode());
		Assert.assertEquals("Exception listing messages.", response.getErrorMessage());
	}
}
