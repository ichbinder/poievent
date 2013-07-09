package de.htw_berlin.opentoken.ApplicationService;

import java.util.List;
import java.util.Set;

import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;

public interface InformationService {

	Event erstelleEvent(Long userId, String title);

	Long istBesitzerVon(Long userId);

	void loescheEvent(Long eventId);

	Set<Event> erstellePoiListe(String poiName);

	boolean validateEvent(Long eventId);

	void addUserToEvent(Long userId, Long eventId);

	Set<Event> findSubscribedEventsBy(Long userId);

	Set<Event> findOwnedEventsBy(Long userId);

	List<Message> getMessage(Long eventId);

	boolean isPartInEvent(Long eventId, Long userId);

	void addMessage(Long eventId, String title, String content);

}
