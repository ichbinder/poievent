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
import de.htw_berlin.opentoken.ApplicationService.InformationService;
import de.htw_berlin.opentoken.ApplicationService.PoiService;
import de.htw_berlin.f4.ai.kbe.poievent.AuthorizationException;
import de.htw_berlin.f4.ai.kbe.poievent.Coordinate;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.f4.ai.kbe.poievent.Poi;
import de.htw_berlin.f4.ai.kbe.poievent.PoiEventFacade;
import de.htw_berlin.opentoken.ApplicationService.InformationService;
import de.htw_berlin.opentoken.ApplicationService.PoiService;
import de.htw_berlin.opentoken.ApplicationService.UserService;


public class PoiEventFacadeImpl implements PoiEventFacade{

	
	@Autowired
	UserService userService;
	@Autowired
	InformationService informationService;
	@Autowired
	PoiService poiService;
	
	
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
			 
		return null;
	}

	public long createEvent(Long userId, String poiName, String title,
			String description) {
		// TODO Auto-generated method stub
		Event event;
		
		if(hasAdminRole(userId))
			event = informationService.erstelleEvent(userId, poiName, title);
		else
			throw new IllegalArgumentException("Ist kein Admin");
		return event.getEventId();
	}

	public void deleteEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		if(hasAdminRole(userId))
		{	
			if(userId == informationService.istBesitzerVon(eventId))	
			{	
				informationService.loescheEvent(eventId);
			}
			else
				throw new IllegalArgumentException("Hat Event nicht angelegt");
		}
		else
			throw new IllegalArgumentException("Ist kein Admin");
	}

	public Set<Event> findEventsForPoi(String poiName) {
		// TODO Auto-generated method stub
		Set<Event> temp;
		if(poiService.validatePoi(poiName))
			temp = informationService.erstellePoiListe(poiName);
		else
			throw new IllegalArgumentException("POI nicht gefunden");
		
		return temp;
	}

	public void subscribeToEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		if(userService.validateUser(userId))
		{
			if(informationService.validateEvent(eventId))
				informationService.addUserToEvent(userId, eventId);
			else
				throw new IllegalArgumentException("Event nicht gefunden");
		}
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public Set<Event> findSubscribedEvents(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findSubscribedEventsBy(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
		
		return temp;
	}

	public Set<Event> findOwnedEvents(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findOwnedEventsBy(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
		
		return temp;
	}

	public List<Message> getMessages(Long eventId) {
		// TODO Auto-generated method stub
		List<Message> temp;
		if(informationService.validateEvent(eventId))
			temp = informationService.getMessage(eventId);
		else
			throw new IllegalArgumentException("Event nicht gefunden");
		return temp;
	}

	public Long createUser(String name, String firstname, String email) {
		// TODO Auto-generated method stub
		if(userService.checkEmail(email))
			return userService.createUser(name, firstname, email);
		else
			throw new IllegalArgumentException("Email schon vergeben");
	}

	public void setAdminRole(Long userId) {
		// TODO Auto-generated method stub
		if(userService.validateUser(userId))
			userService.setAdminRole(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public boolean hasAdminRole(Long userId) {
		// TODO Auto-generated method stub
		if(userService.validateUser(userId))
			return userService.validateAdmin(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public void removeAdminRole(Long userId) {
		// TODO Auto-generated method stub
		if(userService.validateUser(userId))
			userService.removeAdmin(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public Long getUserId(String email) {
		// TODO Auto-generated method stub
		if(userService.validateEmail(email))
			return userService.getUserByEmail(email);
		else
			return null;
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		if(userService.validateUser(userId))
			userService.deleteUserById(userId);
		else
			throw new IllegalArgumentException("User-ID nicht gefunden");
	}

	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		if(userService.validateEmail(email))
			userService.deleteUserByEmail(email);
		else
			throw new IllegalArgumentException("Email-adresse nicht gefunden");
	}

	public void addMessage(Long eventId, Long userId, String title,
			String content) {
		// TODO Auto-generated method stub
		if(informationService.validateEvent(eventId))
		{
			if(informationService.isPartInEvent(eventId, userId))
				informationService.addMessage(eventId, title, content);
		}
		else
			throw new IllegalArgumentException("Event nicht gefunden");
	}
}
