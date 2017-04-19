package com.mycom.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mycom.dao.MessageDAO;
import com.mycom.model.Message;
import com.mycom.service.LangDetectionService;

public class MessageServiceImplTest {

	@Mock
	private LangDetectionService langDetectionService;
	
	@Mock
	private MessageDAO messageDAO;
	
	@InjectMocks
	private MessageServiceImpl messageServiceImpl;
	
	@Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testList_happyPath(){
		List<Message> messages = new LinkedList<Message>();
		Message m = new Message();
		m.setCountry("Argentina");
		m.setInput("Hola, buen dia!");
		messages.add(m);
		Mockito.when(messageDAO.list(Mockito.anyInt(), Mockito.anyString())).thenReturn(messages);
		List<Message> result = messageServiceImpl.list(100, "all");
		Assert.assertEquals(m.getMessage(), result.get(0).getMessage());
	}
	
	@Test
	public void testSave_happyPath(){
		Mockito.when(langDetectionService.detect(Mockito.anyString())).thenReturn("es");
		Message m = messageServiceImpl.submit("Hola, buen dia!", "Argentina");
		Assert.assertEquals(m.getMessage(), "Hola, buen dia! from Argentina!");
	}
	
}
