package com.mycom.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.mycom.dao.MessageDAO;
import com.mycom.model.Message;

public class MessageDAOImpl implements MessageDAO {
	
	private static List<Message> messages = new LinkedList<Message>();

	@Override
	public void save(Message m) {
		messages.add(0,m);
	}

	@Override
	public List<Message> list(Integer numOf, String lang) {
		List<Message> result = new LinkedList<Message>();
		
		for (Message message : messages) {
			if(lang.equals("all") || message.getLang().equals(lang))
				result.add(message);
		}
		
		if(numOf < result.size()){
			result = result.subList(0, numOf);
		}
		
		return result;
	}

}
