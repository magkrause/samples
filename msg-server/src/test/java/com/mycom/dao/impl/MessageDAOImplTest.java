package com.mycom.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mycom.model.Message;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageDAOImplTest {
	
	@InjectMocks
	private MessageDAOImpl messageDAOImpl;
	
	@Before
    public void beforeTest() {
		MockitoAnnotations.initMocks(this);
    	Message m = new Message();
		m.setCountry("Argentina");
		m.setLang("es");
		m.setInput("Hola, buen dia!");
		messageDAOImpl.save(m);
		
		Message m2 = new Message();
		m2.setCountry("United States");
		m2.setLang("en");
		m2.setInput("Hi, good morning!");
		messageDAOImpl.save(m2);
    }
    
	@Test
	public void testList1_happyPath() {
		List<Message> messages = messageDAOImpl.list(1, "es");
		
		Assert.assertEquals(1,messages.size());
		Assert.assertEquals("Hola, buen dia! from Argentina!",messages.get(0).getMessage());
	}  
	
	@Test
	public void testList2_happyPath_all() {
		List<Message> messages = messageDAOImpl.list(1, "all");
		
		Assert.assertEquals(1,messages.size());
		Assert.assertEquals("Hi, good morning! from United States!",messages.get(0).getMessage());
	} 
	
	@Test
	public void testList3_happyPath_twoResultsAll() {
		List<Message> messages = messageDAOImpl.list(2, "all");
		
		Assert.assertEquals(2,messages.size());
		Assert.assertEquals("Hola, buen dia! from Argentina!",messages.get(1).getMessage());
		Assert.assertEquals("Hi, good morning! from United States!",messages.get(0).getMessage());
	} 
	
	@Test
	public void testList4_happyPath_twoResultsEn() {
		List<Message> messages = messageDAOImpl.list(1, "en");
		
		Assert.assertEquals(1,messages.size());
		Assert.assertEquals("Hi, good morning! from United States!",messages.get(0).getMessage());
	} 
	
	@Test
	public void testList5_happyPath_twoResultsLongNum() {
		List<Message> messages = messageDAOImpl.list(100, "all");
		
		Assert.assertEquals(10,messages.size());
		Assert.assertEquals("Hi, good morning! from United States!",messages.get(0).getMessage());
		Assert.assertEquals("Hola, buen dia! from Argentina!",messages.get(1).getMessage());
		Assert.assertEquals("Hi, good morning! from United States!",messages.get(2).getMessage());
		Assert.assertEquals("Hola, buen dia! from Argentina!",messages.get(3).getMessage());
	} 
}
