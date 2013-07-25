package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class OwnEventTest extends OwnTestPoiInit{
	
	protected long eventId1;
	protected long eventId2;
	protected long eventId3;
	protected long eventId4;
	
	@Test
	public void createEvent()
	{
		eventId1 = facadeImplTest.createEvent(testUser, musterPoi.getName() , "testEvent1" , "TestDescription1");
		eventId2 = facadeImplTest.createEvent(testUser, musterPoi.getName() , "testEvent2" , "TestDescription2");
		eventId3 = facadeImplTest.createEvent(testUser, musterPoi.getName() , "testEvent3" , "TestDescription3");
		eventId4 = facadeImplTest.createEvent(testUser, musterPoi.getName() , "testEvent4" , "TestDescription4");
		assertEquals(4, facadeImplTest.findOwnedEvents(testUser).size());
		facadeImplTest.deleteEvent(testUser, eventId1);
		facadeImplTest.deleteEvent(testUser, eventId3);
		assertEquals(2, facadeImplTest.findOwnedEvents(testUser).size());
		facadeImplTest.deletePOI(testAdmin, poiName);
		assertEquals(0, facadeImplTest.findOwnedEvents(testUser).size());
	}
	
	@Test
	public void addMessage()
	{
		
		facadeImplTest.createSimplePOI(testAdmin, "poiName2", tagsInit(), 13.526187f, 52.457735f);
		
		Message message = messageInit();
		long messageTestEventId = facadeImplTest.createEvent(testAdmin, "poiName2", 
															 "Test event", "Test event description");
		
		facadeImplTest.addMessage(messageTestEventId, testAdmin, "Test" , "Test description");
		
		List<Message> tempListMessage = facadeImplTest.getMessages(messageTestEventId);
		assertEquals(1, facadeImplTest.getMessages(messageTestEventId).size());	
		for(Message i : tempListMessage)
		{	
			assertEquals(message.getDescription(), i.getDescription());
			assertEquals(message.getTitle(), i.getTitle());
		}
		
		facadeImplTest.addMessage(messageTestEventId, testAdmin, "Test2" , "Test Desc. 2");
		facadeImplTest.addMessage(messageTestEventId, testAdmin, "Test3" , "Test Desc. 3");
		facadeImplTest.addMessage(messageTestEventId, testAdmin, "Test4" , "Test Desc. 4");
		assertEquals(4, facadeImplTest.getMessages(messageTestEventId).size());
	}
	
	
	public Message messageInit()
	{
		Message tempMessage = new Message();
		
		tempMessage.setTitle("Test");
		tempMessage.setDescription("Test description");
		
		return tempMessage;
	}
}
