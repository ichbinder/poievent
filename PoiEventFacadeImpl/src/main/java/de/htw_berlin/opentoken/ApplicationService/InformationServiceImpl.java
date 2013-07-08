package de.htw_berlin.opentoken.ApplicationService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.f4.ai.kbe.springdatarepository.InformationRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.UserRepository;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationRepository informationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public Event erstelleEvent(Long userId, String poiName, String title) {
		// TODO Auto-generated method stub
		Event event = new Event(userRepository.findBy(userId));
		
		return event;
	}

	@Override
	public Long istBesitzerVon(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loescheEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Event> erstellePoiListe(String poiName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateEvent(Long eventId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addUserToEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Event> findSubscribedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Event> findOwnedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessage(Long eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateEventByUser(Long eventId, Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPartInEvent(Long eventId, Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addMessage(Long eventId, String title, String content) {
		// TODO Auto-generated method stub
		
	}

}
