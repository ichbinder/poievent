package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateSimplePoiTest extends TestUserInit{

	
	
	@Test
	public void testCreateSimplePoi() {
		//act
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		Poi actual = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
		
		Poi expected = new SimplePoi(PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
			
		//verify
		assertEquals(expected, actual);
		poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
	}
	/*
	@Test
	public void testCreateSimplePoiDuplicate() {
		//act
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		
		try{
			poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		}
		catch(IllegalArgumentException e){
			poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
			return;
		}
		fail();
	}
	
	
	@Test
	public void testCreatePoiDuplicate() {
		//act
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);		
		try{
			poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, "Street 1a", "Hamburg", PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		}
		catch(IllegalArgumentException e){
			poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
			return;
		}
		fail();
	}
	
	
	@Test(expected=AuthorizationException.class)
	public void testCreateSimplePoiWithoutPermissions() {
		poiEvent.createSimplePOI(userId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutValidUserID() {
		poiEvent.createSimplePOI(7373620223786557L, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, -90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutNullUserID() {
		poiEvent.createSimplePOI(null, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, -90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeOutOfRange() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, -90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeOutOfRange1() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeOutOfRange() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, -180.1f);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeOutOfRange1() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, 180.1f);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeNull() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, PoiEventTestConstants.HTW_LATITUDE, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeNull() {
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, null, PoiEventTestConstants.HTW_LONGITUDE);
	}
	*/
}
