package de.htw_berlin.opentoken.ApplicationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Message;
import de.htw_berlin.opentoken.model.EventModel;
import de.htw_berlin.opentoken.model.MessageModel;
import de.htw_berlin.opentoken.model.UserModel;
import de.htw_berlin.opentoken.springdatarepository.EventRepository;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;



@Service
public class InformationServiceImpl implements InformationService {

	private static final Logger logger = Logger.getLogger(InformationServiceImpl.class); 
	
	@Autowired
	PoiRepository poiRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	
	@Override
	@Transactional
	public long erstelleEvent(Long userId, String title, String description) {
		EventModel eventModel = new EventModel(title, description,userRepository.findOne(userId));
		UserModel userModel = userRepository.findOne(userId);
		userModel.addOwnedEvent(eventModel);
		
		eventRepository.saveAndFlush(eventModel);
		userRepository.saveAndFlush(userModel);
		
		logger.info("Event mit den Daten erstellt und Event dem User hinzugefügt");
		return eventModel.getEventId();	
	}

	@Override
	@Transactional
	public Long istBesitzerVon(Long eventId) {
		// TODO Auto-generated method stub
		EventModel eventModel = eventRepository.findOne(eventId);
		logger.info("Event per EventId gesucht");
		return eventModel.getOwner().getUserId();
	}

	@Override
	@Transactional
	public void loescheEvent(Long eventId) {
		// TODO Auto-generated method stub
		eventRepository.delete(eventId);
		logger.info("Event per EventId gelöscht");
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
		logger.info("Event per EventId auf Existenz geprüft");
		return checkEvent;
	}

	@Override
	@Transactional
	public void addUserToEvent(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		EventModel eventModel = eventRepository.findOne(eventId);
		UserModel userModel = userRepository.findOne(userId);
		eventModel.addSubscribtion(userModel);
		userModel.addSubscriptionFor(eventModel);
		
		userRepository.saveAndFlush(userModel);
		eventRepository.saveAndFlush(eventModel);
		logger.info("User einem Event hinzugefügt");
	}

	@Override
	@Transactional
	public Set<Event> findSubscribedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp = new HashSet<Event>();
		Set<EventModel> eventModelList = userRepository.findOne(userId).getSubscriptedEvent();
		Event tempEvent = new Event();
		for(EventModel i : eventModelList)
		{	
			tempEvent.setTitle(i.getTitle());
			tempEvent.setDate(i.getDate());
			tempEvent.setDescription(i.getDescription());
		
			temp.add(tempEvent);
		}
		logger.info("Event für die sich ein User angemeldet hat suchen");
		return temp;
	}

	@Override
	@Transactional
	public Set<Event> findOwnedEventsBy(Long userId) {
		// TODO Auto-generated method stub
		Set<Event> temp = new HashSet<Event>();
		Set<EventModel> eventModelList = userRepository.findOne(userId).getOwnedEvents();
		Event tempEvent = new Event();
		for(EventModel i : eventModelList)
		{	
			tempEvent.setTitle(i.getTitle());
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
		List<Message> temp = new ArrayList<Message>();
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

	@Override
	public Event getEventByEventId(Long eventId) {
		// TODO Auto-generated method stub
		EventModel eventModel = eventRepository.findOne(eventId);
		Event temp = new Event();
		
		temp.setTitle(eventModel.getTitle());
		temp.setDescription(eventModel.getDescription());
		temp.setDate(eventModel.getDate());
		return temp;
	}
}
