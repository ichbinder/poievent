package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.assertEquals;

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
		
	}
}
