package de.htw_berlin.opentoken.ApplicationService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.model.User;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.f4.ai.kbe.springdatarepository.PoiRepository;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	PoiRepository poiRepository;
	
	@Override
	@Transactional
	public Event erstelleEvent(Long userId, String poiName, String title) {
		User user = poiRepository.findOne(userId);
		if (user == null)
			throw new IllegalArgumentException();
		user.checkAdmin();
		return null;
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
