package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CreatePolygonPoiTest extends TestUserInit{

	
	Coordinate valid1= new Coordinate(30.03f, 50.3f);
	Coordinate valid2= new Coordinate(31.03f, 51.3f);
	Coordinate valid3= new Coordinate(31.03f, 50.3f);
	Coordinate notValid = new Coordinate(0.0f, 181.1f);
	
	List<Coordinate> validPolygon = new ArrayList<Coordinate>(
		Arrays.asList(valid1,valid2, valid3));
	
	List<Coordinate> notValidPolygon = new ArrayList<Coordinate>(
			Arrays.asList(valid1,valid2,notValid,valid3));
	
	@Test
	public void testCreatePolygonPOI() {
		
		//act
		poiEvent.createPolygonPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, validPolygon);
		Poi actual = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
		
		Poi expected = new PolygonPoi(PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, validPolygon);
			
		//verify
		assertEquals(expected, actual);
	}
	
	@Test(expected=AuthorizationException.class)
	public void testCreatePolygonPOIWithoutPermissions() {
		poiEvent.createPolygonPOI(userId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 
				validPolygon);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutValidUserID() {
		poiEvent.createPolygonPOI(7373620223786557L, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, validPolygon);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCreatePoiWithoutNullUserID() {
		poiEvent.createPolygonPOI(null, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, notValidPolygon);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testLongitudeNull() {
		poiEvent.createPolygonPOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, null);
	}

	
}
