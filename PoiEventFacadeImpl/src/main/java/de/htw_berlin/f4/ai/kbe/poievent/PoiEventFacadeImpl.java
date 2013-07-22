package de.htw_berlin.f4.ai.kbe.poievent;


import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.ApplicationService.InformationService;
import de.htw_berlin.opentoken.ApplicationService.PoiService;
import de.htw_berlin.opentoken.ApplicationService.UserService;


public class PoiEventFacadeImpl implements PoiEventFacade{

	private static final Logger logger = Logger.getLogger(PoiEventFacadeImpl.class); 
	
	@Autowired
	UserService userService;
	@Autowired
	InformationService informationService;
	@Autowired
	PoiService poiService;
	
	
	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude) {
		poiService.createSimplePOI(userId, name, tags, latitude, longitude);
	}

	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude)
			throws AuthorizationException {
		poiService.createCityPOI(userId, name, tags, street, city, latitude, longitude);
	}

	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		poiService.createPolygonPOI(userId, name, tags, polygon);
	}

	public void deletePOI(Long userId, String name) {
		poiService.deletePOI(userId, name);
	}

	public void addPoiTag(Long userId, String name, String tag) {
		if(hasAdminRole(userId))
			poiService.addPoiTag(userId, name, tag);
		else
		{
			logger.error("User besitzt keine Adminrechte");
			throw new AuthorizationException(userId);
		}
	}

	public void deletePoiTag(Long userId, String name, String tag) {
		if(hasAdminRole(userId))
			poiService.deletePoiTag(userId, name, tag);
		else
		{
			logger.error("User besitzt keine Adminrechte");
			throw new AuthorizationException(userId);	
		}
	}

	public Set<Poi> getPoiByTag(String tag) {
		return poiService.getPoiByTag(tag);
	}

	public Poi getPoi(String name) {
		return poiService.getPoi(name);
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
			{
				logger.error("User nicht existent");
				throw new IllegalArgumentException("Ist kein Admin");
			}
		}
		else
		{
			logger.error("Point of Interest nicht gefunden");
			throw new IllegalArgumentException("Kein PoI mit dem Namen gefunden");
		}
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
				{
					logger.error("User besitzt keine Adminrechte");
					throw new SecurityException("Hat den Event nicht angelegt oder ist kein Admin.");
				}
			}
			else
			{
				logger.error("User nicht gefunden");
				throw new IllegalArgumentException("Benutzer nicht gefunden");
			}
		}
		else
		{
			logger.error("Event nicht gefunden");
			throw new IllegalArgumentException("Event nicht gefunden.");
		}
	}

	public Set<Event> findEventsForPoi(String poiName) {
		Set<Event> temp;
		if(poiService.validatePoi(poiName))
			temp = poiService.getAllEventsByPoi(poiName);
		else
		{
			logger.error("Point of Interest nicht gefunden");
			throw new IllegalArgumentException("POI nicht gefunden");
		}
		return temp;
	}

	public void subscribeToEvent(Long userId, Long eventId) {
		if(userService.validateUser(userId))
		{
			if(informationService.validateEvent(eventId))
				informationService.addUserToEvent(userId, eventId);
			else
			{
				logger.error("Event nicht gefunden");
				throw new IllegalArgumentException("Event nicht gefunden");
			}
		}
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
	}

	public Set<Event> findSubscribedEvents(Long userId) {
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findSubscribedEventsBy(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
		return temp;
	}

	public Set<Event> findOwnedEvents(Long userId) {
		Set<Event> temp;
		if(userService.validateUser(userId))
			temp = informationService.findOwnedEventsBy(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
		return temp;
	}

	public List<Message> getMessages(Long eventId) {
		List<Message> temp;
		if(informationService.validateEvent(eventId))
			temp = informationService.getMessage(eventId);
		else
		{
			logger.error("Event nicht gefunden");
			throw new IllegalArgumentException("Event nicht gefunden");
		}
		
		return temp;
	}

	public Long createUser(String name, String firstname, String email) {
		if(!userService.checkEmail(email))
			return userService.createUser(name, firstname, email);
		else
		{
			logger.error("Email schon vorhanden");
			throw new IllegalArgumentException("Email schon vergeben");
		}
	}

	public void setAdminRole(Long userId) {

		if(userService.validateUser(userId))
			userService.setAdminRole(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
	}

	public boolean hasAdminRole(Long userId) {
		if(userService.validateUser(userId))
			return userService.validateAdmin(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
	}

	public void removeAdminRole(Long userId) {
		if(userService.validateUser(userId))
			userService.removeAdmin(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User nicht gefunden");
		}
	}

	public Long getUserId(String email) {
		if(userService.validateEmail(email))
			return userService.getUserByEmail(email);
		else
		{	
			logger.error("Email des Users gefunden");
			return null;
		}
	}

	public void deleteUser(Long userId) {
		if(userService.validateUser(userId))
			userService.deleteUserById(userId);
		else
		{
			logger.error("User nicht gefunden");
			throw new IllegalArgumentException("User-ID nicht gefunden");
		}
	}

	public void deleteUser(String email) {
		if(userService.validateEmail(email))
			userService.deleteUserByEmail(email);
		else
		{
			logger.error("Email nicht gefunden");
			throw new IllegalArgumentException("Email-adresse nicht gefunden");
		}
	}

	public void addMessage(Long eventId, Long userId, String title,
			String content) {
		if(informationService.validateEvent(eventId))
		{
			if(informationService.isPartInEvent(eventId, userId))
				informationService.addMessage(eventId, title, content);
			else
			{
				logger.error("User nicht am Event angemeldet");
				throw new IllegalArgumentException("User hat sich nicht für das Event angemeldet");
			}	
		}
		else
		{
			logger.error("Event nicht gefunden");
			throw new IllegalArgumentException("Event nicht gefunden");
		}	
	}
}
