package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeletePoiTest extends TestUserInit{

	
	
	
	@Test
	public void deletePoi() {

		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 
				PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		
		poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
	}

	@Test(expected=AuthorizationException.class)
	public void testCreatePolygonPOIWithoutPermissions() {
		
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 
				PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		try{
			poiEvent.deletePOI(userId, PoiEventTestConstants.HTW_NAME);
		} catch (AuthorizationException e) {
			poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
			return;
		}
		fail();
	}
	
	
}
