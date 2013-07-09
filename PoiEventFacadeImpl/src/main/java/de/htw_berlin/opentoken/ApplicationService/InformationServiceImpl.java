package de.htw_berlin.opentoken.ApplicationService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.model.EventModel;
import de.htw_berlin.f4.ai.kbe.model.MessageModel;
import de.htw_berlin.f4.ai.kbe.model.UserModel;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.f4.ai.kbe.springdatarepository.EventRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.PoiRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.UserRepository;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	PoiRepository poiRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	
	@Override
	@Transactional
	public Event erstelleEvent(Long userId, String poiName, String title) {
		
	}

	@Override
	@Transactional
	public Long istBesitzerVon(Long eventId) {
		// TODO Auto-generated method stub
		EventModel eventModel = eventRepository.findOne(eventId);

		return eventModel.getOwner().getUserId();
	}

	@Override
	@Transactional
	public void loescheEvent(Long eventId) {
		// TODO Auto-generated method stub
		eventRepository.delete(eventId);
	}

	@Override
	@Transactional
	public Set<Event> erstellePoiListe(String poiName) {
		// TODO Auto-generated method stub
		Set<Event> temp;
		
		return null;
	}

	@Override
	@Transactional
	public boolean validateEvent(Long eventId) {
		// TODO Auto-generated method stub
		boolean checkEvent = false;
		EventModel eventModel = eventRepository.findOne(eventId);
		if(eventModel != null)
		{
			checkEvent = true;
		}
		return checkEvent;
	}

	@Override
	@Transactional
	public void addUserToEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		EventModel eventModel = eventRepository.findOne(eventId);
		UserModel userModel = userRepository.findOne(userId);
		eventModel.addSubscribtion(userModel);
		eventRepository.saveAndFlush(eventModel);
	}

	@Override
	@Transactional
	public Set<Event> findSubscribedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp = new HashSet<Event>();
		Set<EventModel> eventModelList = eventRepository.findSubscriptionFor(userRepository.findOne(userId));
		Event tempEvent = new Event();
		for(EventModel i : eventModelList)
		{	
			tempEvent.setTitle(i.getTitle());
			tempEvent.setEventId(i.getEventId());
			tempEvent.setDate(i.getDate());
			tempEvent.setDescription(i.getDescription());
		
			temp.add(tempEvent);
		}
		return temp;
	}

	@Override
	@Transactional
	public Set<Event> findOwnedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp = new HashSet<Event>();
		Set<EventModel> eventModelList = eventRepository.findOwnerFor(userRepository.findOne(userId));
		Event tempEvent = new Event();
		for(EventModel i : eventModelList)
		{	
			tempEvent.setTitle(i.getTitle());
			tempEvent.setEventId(i.getEventId());
			tempEvent.setDate(i.getDate());
			tempEvent.setDescription(i.getDescription());
			
			temp.add(tempEvent);
		}
		return temp;
	}

	@Override
	@Transactional
	public List<Message> getMessage(Long eventId) {
		// TODO Auto-generated method stub
		List<Message> temp = null;
		List<MessageModel> tempModel;
		Message buffer = new Message();
		EventModel eventModel = eventRepository.findOne(eventId);
		tempModel = eventModel.getMessage();
		
		for(MessageModel i: tempModel)
		{

			buffer.setTitle(i.getTitle());
			buffer.setDescription(i.getDescription());
			temp.add(buffer);
		}
		return temp;
	}

	@Override
	@Transactional
	public boolean isPartInEvent(Long eventId, Long userId) {
		// TODO Auto-generated method stub
		boolean checkValue = false;
		EventModel eventModel = eventRepository.findOne(eventId);
		Set<UserModel> setSubscribed = eventModel.getSubscribted();
		
		for(UserModel i: setSubscribed)
		{
			if(userId == i.getUserId())
				checkValue = true;
		}
		return checkValue;
	}

	@Override
	@Transactional
	public void addMessage(Long eventId, String title, String content) {
		// TODO Auto-generated method stub
		MessageModel messageModel = new MessageModel(title, content);
		EventModel eventModel = eventRepository.findOne(eventId);
		eventModel.addMessage(messageModel);
		eventRepository.saveAndFlush(eventModel);
	}

}
