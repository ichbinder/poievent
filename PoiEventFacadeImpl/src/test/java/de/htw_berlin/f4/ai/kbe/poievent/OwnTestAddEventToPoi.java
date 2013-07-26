package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.ApplicationService.InformationService;
import de.htw_berlin.opentoken.ApplicationService.PoiService;
import de.htw_berlin.opentoken.model.UserModel;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

public class OwnTestAddEventToPoi extends OwnTestPoiInit {
	
	@Autowired
	PoiService poiService;
	@Autowired
	InformationService informationService;
	@Autowired
	UserRepository userRepository;
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventToPoiWithoutNullUserID() {
		long eventId = informationService.erstelleEvent(testUser, "Lan-Party", "Viel CS und Quake spielen.");
		poiService.addEventToPoi(eventId, null, poiName);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventToPoiWithoutPermissions() {
		long eventId = informationService.erstelleEvent(testUser, "Lan-Party", "Viel CS und Quake spielen.");
		UserModel user = new UserModel("Mueller", "Horst", "horst@gmail.com");
		user.setUserId(7373620223786557L);
		poiService.addEventToPoi(eventId, user, poiName);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventToPoiWithoutPoi() {
		long eventId = informationService.erstelleEvent(testUser, "Lan-Party", "Viel CS und Quake spielen.");
		poiService.addEventToPoi(eventId, userRepository.findOne(testUser), createSimplePoi().getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventToPoiWithoutEvent() {
		poiService.addEventToPoi(7373620223786557L, userRepository.findOne(testUser), poiName);
	}
	
}
