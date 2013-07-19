package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest extends TestUserInit {
	
	long event1Id, event2Id;

	private void initPoi() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME,
				PoiEventTestConstants.HTW_TAGS,
				PoiEventTestConstants.HTW_LATITUDE,
				PoiEventTestConstants.HTW_LONGITUDE);
		event1Id = poiEvent.createEvent(userId, PoiEventTestConstants.HTW_NAME,
				"Party", "very interesting party");
		event2Id = poiEvent.createEvent(userId, PoiEventTestConstants.HTW_NAME,
				"Konzert", "Ein Konzert ...");
	}

	void deletePoi() {
		poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
	}

	private long addUser2() {
		return poiEvent.createUser("Meier", "Klaus", "user2@htw-berlin.de");
	}

	private void deleteUser2() {
		poiEvent.deleteUser("user2@htw-berlin.de");
	}

	@Test
	public void testDeleteWithoutPermissions() {
		initPoi();
		long id = addUser2();
		try {
			poiEvent.deleteEvent(id, event1Id);
		} catch (AuthorizationException e) {
			return;
		} finally {
			deleteUser2();
		}
		fail();
	}

	@Test
	public void testDeleteAsAdmin() {
		initPoi();
		long id = addUser2();
		long event3Id = poiEvent.createEvent(id, PoiEventTestConstants.HTW_NAME,
				"Party2", "very interesting party2");
		assertEquals(3,
				poiEvent.findEventsForPoi(PoiEventTestConstants.HTW_NAME)
						.size());
		poiEvent.deleteEvent(adminId, event3Id);
		assertEquals(2,
				poiEvent.findEventsForPoi(PoiEventTestConstants.HTW_NAME)
						.size());
		deleteUser2();
	}

	@Test
	public void testCreateDelete() {
		initPoi();
		assertEquals(2,
				poiEvent.findEventsForPoi(PoiEventTestConstants.HTW_NAME)
						.size());
		assertEquals(2, poiEvent.findOwnedEvents(userId).size());
		poiEvent.deleteEvent(userId, event1Id);
		assertEquals(1,
				poiEvent.findEventsForPoi(PoiEventTestConstants.HTW_NAME)
						.size());
		event1Id = poiEvent.createEvent(userId, PoiEventTestConstants.HTW_NAME,
				"Party", "very interesting party");
		deletePoi();
	}

	@Test
	public void testCascadeDelete() {
		initPoi();
		deletePoi();
		assertEquals(0, poiEvent.findOwnedEvents(userId).size());
	}

	@Test
	public void testFindOwnedEvents() {
		initPoi();
		assertEquals(2, poiEvent.findOwnedEvents(userId).size());
		deletePoi();
	}

	@Test
	public void testSubscribe() {
		initPoi();
		long user2Id = addUser2();
		poiEvent.subscribeToEvent(user2Id, event1Id);
		assertEquals(1, poiEvent.findSubscribedEvents(user2Id).size());
		poiEvent.subscribeToEvent(user2Id, event2Id);
		assertEquals(2, poiEvent.findSubscribedEvents(user2Id).size());
		deleteUser2();
		deletePoi();
	}
	
	@Test
	public void testAddMessages() {
		initPoi();
		assertEquals(0, poiEvent.getMessages(event1Id));
		poiEvent.addMessage(event1Id, userId, "Nachricht", "zu empfehlen");
		assertEquals(1, poiEvent.getMessages(event1Id).size());
		deletePoi();
	}
	
	@Test
	public void testFindMessages() {
		initPoi();
		poiEvent.addMessage(event1Id, userId, "Nachricht", "zu empfehlen");
		assertEquals("Nachricht", poiEvent.getMessages(event1Id).get(0).getTitle() );
		deletePoi();
	}
	
	
	@Test
	public void testDeleteMessagesWithInvalidUserId() {
		initPoi();
		try {
			poiEvent.deleteEvent(746374645734L, event1Id);
		} catch (IllegalArgumentException e) {
			return;
		}finally{
			deletePoi();
		}
		fail();
	}
}
