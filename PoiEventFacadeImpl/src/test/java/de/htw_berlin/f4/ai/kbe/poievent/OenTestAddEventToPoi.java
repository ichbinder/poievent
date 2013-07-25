package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.ApplicationService.InformationService;
import de.htw_berlin.opentoken.ApplicationService.PoiService;

public class OenTestAddEventToPoi extends OwnTestPoiInit {
	
	@Autowired
	PoiService poiService;
	@Autowired
	InformationService informationService;
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventToPoiWithoutNullUserID() {
		long eventId = informationService.erstelleEvent(testUser, "Lan-Party", "Viel CS und Quake spielen.");
		poiService.addEventToPoi(eventId, null, poiName);
	}
}
