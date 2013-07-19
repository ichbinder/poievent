package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class CreateCityPoiTest extends TestUserInit{

	final static String HTW_STREET = "Wilhelminenhofstraße 75a"; 
	final static String BERLIN = "Berlin";
	

	@Test
	public void testcreateCityPoi() {
		//act
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN, 
				PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		Poi actual = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
		
		
		Poi expected = new CityPoi(PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN,
			 PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
			
		//verify
		assertEquals(expected, actual);
	}
	/*
	@Test(expected=AuthorizationException.class)
	public void testcreateCityPoiWithoutPermissions() {
		poiEvent.createCityPOI(userId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN, 
				PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutValidUserID() {
		poiEvent.createCityPOI(7373620223786557L, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 
				HTW_STREET, BERLIN, -90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutNullUserID() {
		poiEvent.createCityPOI(null, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN
				,-90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeOutOfRange() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN,
				-90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeOutOfRange1() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN
				,90.1f, PoiEventTestConstants.HTW_LONGITUDE);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeOutOfRange() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN
				,PoiEventTestConstants.HTW_LATITUDE, -180.1f);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeOutOfRange1() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN,
				PoiEventTestConstants.HTW_LATITUDE, 180.1f);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeNull() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN,
				PoiEventTestConstants.HTW_LATITUDE, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeNull() {
		poiEvent.createCityPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, HTW_STREET, BERLIN,
				null, PoiEventTestConstants.HTW_LONGITUDE);
	}*/
	
}
