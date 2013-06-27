package de.htw_berlin.f4.ai.kbe.poievent;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.f4.ai.kbe.poievent.AuthorizationException;
import de.htw_berlin.f4.ai.kbe.poievent.Coordinate;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.f4.ai.kbe.poievent.Poi;
import de.htw_berlin.f4.ai.kbe.poievent.PoiEventFacade;
import de.htw_berlin.opentoken.ApplicationService.AddUser;
import de.htw_berlin.opentoken.ApplicationService.AnApplicationService;


public class PoiEventFacadeImpl implements PoiEventFacade{

	
	@Autowired
	AnApplicationService anApplicationService;
	
	@Autowired
	AddUser addUser;
	
	
	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude) {
		
		
		
		// TODO Auto-generated method stub
		
	}

	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		// TODO Auto-generated method stub
		
	}

	public void deletePOI(Long userId, String name) {
		// TODO Auto-generated method stub
		
	}

	public void addPoiTag(Long userId, String name, String tag) {
		// TODO Auto-generated method stub
		
	}

	public void deletePoiTag(Long userId, String name, String tag) {
		// TODO Auto-generated method stub
		
	}

	public Set<Poi> getPoiByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public Poi getPoi(String name) {
		// TODO Auto-generated method stub
		
		//for dummy test
	 
		anApplicationService.doSomeThing();
		 
		return null;
	}

	public long createEvent(Long userId, String poiName, String title,
			String description) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		
	}

	public Set<Event> findEventsForPoi(String poiName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void subscribeToEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		
	}

	public Set<Event> findSubscribedEvents(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Event> findOwnedEvents(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Message> getMessages(Long eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long createUser(String name, String firstname, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAdminRole(Long userId) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasAdminRole(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeAdminRole(Long userId) {
		// TODO Auto-generated method stub
		
	}

	public Long getUserId(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		
	}

	public void addMessage(Long eventId, Long userId, String title,
			String content) {
		// TODO Auto-generated method stub
		
	}



	
	
}
