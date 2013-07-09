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
		if(hasAdminRole(userId))
			poiService.createSimplePOI(userId, name, tags, latitude, longitude);
		else
			throw new AuthorizationException(userId);
	}

	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude)
			throws AuthorizationException {
		if(hasAdminRole(userId))
			poiService.createCityPOI(userId, name, tags, street, city, latitude, longitude);
		else
			throw new AuthorizationException(userId);
	}

	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		if(hasAdminRole(userId))
			poiService.createPolygonPOI(userId, name, tags, polygon);
		else
			throw new AuthorizationException(userId);
	}

	public void deletePOI(Long userId, String name) {
		if(hasAdminRole(userId))
			poiService.deletePOI(userId, name);
		else
			throw new AuthorizationException(userId);
	}

	public void addPoiTag(Long userId, String name, String tag) {
		if(hasAdminRole(userId))
			poiService.addPoiTag(userId, name, tag);
		else
			throw new AuthorizationException(userId);
	}

	public void deletePoiTag(Long userId, String name, String tag) {
		if(hasAdminRole(userId))
			poiService.deletePoiTag(userId, name, tag);
		else
			throw new AuthorizationException(userId);		
	}

	public Set<Poi> getPoiByTag(String tag) {
		return poiService.getPoiByTag(tag);
	}

	public Poi getPoi(String name) {
		return getPoi(name);
	}

	public long createEvent(Long userId, String poiName, String title, String description) {
		long eventId;
		Event event;
		if(poiService.getPoi(poiName) != null)
		{	
			if(userService.validateUser(userId))
			{	
				eventId = informationService.erstelleEvent(userId, title, description);
				event = informationService.getEventByEventId(eventId);
				poiService.addEvent(event,userService.getUserById(userId), poiName);
			}	
			else
				throw new IllegalArgumentException("Ist kein Admin");
		}
		else
			throw new IllegalArgumentException("Kein PoI mit dem Namen gefunden");
		
		return eventId;
	}

	public void deleteEvent(Long userId, Long eventId) {
		if(informationService.validateEvent(eventId))
		{	
			if(userService.validateUser(userId))
			{	
				if(hasAdminRole(userId) || userId == informationService.istBesitzerVon(eventId))	
				{	
					informationService.loescheEvent(eventId);
				}
				else
					throw new SecurityException("Hat den Event nicht angelegt oder ist kein Admin.");
			}
			else
				throw new IllegalArgumentException("Benutzer nicht gefunden");
		}
		else
			throw new IllegalArgumentException("Event nicht gefunden.");
	}

	public Set<Event> findEventsForPoi(String poiName) {
		Set<Event> temp;
		if(poiService.validatePoi(poiName))
			temp = poiService.getAllEventsByPoi(poiName);
		else
			throw new IllegalArgumentException("POI nicht gefunden");
		
		return temp;
	}

	public void subscribeToEvent(Long userId, Long eventId) {
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
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findSubscribedEventsBy(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
		
		return temp;
	}

	public Set<Event> findOwnedEvents(Long userId) {
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findOwnedEventsBy(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
		
		return temp;
	}

	public List<Message> getMessages(Long eventId) {
		List<Message> temp;
		if(informationService.validateEvent(eventId))
			temp = informationService.getMessage(eventId);
		else
			throw new IllegalArgumentException("Event nicht gefunden");
		return temp;
	}

	public Long createUser(String name, String firstname, String email) {
		if(userService.checkEmail(email))
			return userService.createUser(name, firstname, email);
		else
			throw new IllegalArgumentException("Email schon vergeben");
	}

	public void setAdminRole(Long userId) {

		if(userService.validateUser(userId))
			userService.setAdminRole(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public boolean hasAdminRole(Long userId) {
		if(userService.validateUser(userId))
			return userService.validateAdmin(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public void removeAdminRole(Long userId) {
		if(userService.validateUser(userId))
			userService.removeAdmin(userId);
		else
			throw new IllegalArgumentException("User nicht gefunden");
	}

	public Long getUserId(String email) {
		if(userService.validateEmail(email))
			return userService.getUserByEmail(email);
		else
			return null;
	}

	public void deleteUser(Long userId) {
		if(userService.validateUser(userId))
			userService.deleteUserById(userId);
		else
			throw new IllegalArgumentException("User-ID nicht gefunden");
	}

	public void deleteUser(String email) {
		if(userService.validateEmail(email))
			userService.deleteUserByEmail(email);
		else
			throw new IllegalArgumentException("Email-adresse nicht gefunden");
	}

	public void addMessage(Long eventId, Long userId, String title,
			String content) {
		if(informationService.validateEvent(eventId))
		{
			if(informationService.isPartInEvent(eventId, userId))
				informationService.addMessage(eventId, title, content);
		}
		else
			throw new IllegalArgumentException("Event nicht gefunden");
	}
}
